package wf.garnier.spring.security.seven.mfa;

import java.time.Duration;
import java.util.List;
import java.util.function.Supplier;

import org.jspecify.annotations.Nullable;
import wf.garnier.spring.security.seven.mfa.ott.MailNotifier;
import wf.garnier.spring.security.seven.mfa.user.DemoUser;
import wf.garnier.spring.security.seven.mfa.user.DemoUserDetailsService;

import org.springframework.boot.web.server.autoconfigure.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authorization.AllRequiredFactorsAuthorizationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.authorization.AuthorizationManagerFactories;
import org.springframework.security.authorization.AuthorizationResult;
import org.springframework.security.authorization.RequiredFactor;
import org.springframework.security.config.annotation.authorization.EnableMultiFactorAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.FactorGrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.DelegatingMissingAuthorityAccessDeniedHandler;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMultiFactorAuthentication(authorities = {})
// (authorities = { FactorGrantedAuthority.PASSWORD_AUTHORITY,
// FactorGrantedAuthority.OTT_AUTHORITY })
class SecurityConfiguration {

	// @Bean
	// AuthorizationManagerFactory<Object> authz() {
	// return AuthorizationManagerFactories.multiFactor()
	// .requireFactors(
	// FactorGrantedAuthority.PASSWORD_AUTHORITY,
	// FactorGrantedAuthority.OTT_AUTHORITY
	// )
	// .build();
	// }

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, MailNotifier mailNotifier,
			ServerProperties serverProperties) {
		var mfa = AuthorizationManagerFactories.multiFactor()
			.requireFactors(FactorGrantedAuthority.PASSWORD_AUTHORITY, FactorGrantedAuthority.OTT_AUTHORITY)
			.build();

		var passwordLastMinute = AuthorizationManagerFactories.multiFactor()
			.requireFactor((factor) -> factor.passwordAuthority().validDuration(Duration.ofSeconds(30)))
			.build();

		AuthorizationManager<RequestAuthorizationContext> adminsMustMfa = new AuthorizationManager<>() {

			@Override
			public @Nullable AuthorizationResult authorize(Supplier<? extends @Nullable Authentication> authentication,
					RequestAuthorizationContext object) {
				var mfa = AllRequiredFactorsAuthorizationManager.builder()
					.requireFactor(RequiredFactor.builder().passwordAuthority().build())
					.requireFactor(RequiredFactor.builder().ottAuthority().build())
					.build();
				var auth = authentication.get();
				if (auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch("ROLE_admin"::equals)) {
					return mfa.authorize(authentication, object);
				}
				return passwordLastMinute.authenticated().authorize(authentication, object);
			}
		};

		//@formatter:off
		return http
				.authorizeHttpRequests(authz -> {
					authz.requestMatchers("/", "/css/**", "/favicon.ico", "/favicon.svg", "/error").permitAll();
					authz.requestMatchers("/daniel").access((authSupplier, reqContext) -> {
						if (authSupplier.get().getName().equals("daniel")) {
							return new AuthorizationDecision(true);
						}
						return new AuthorizationDecision(false);
					});
					authz.requestMatchers("/admin").access(mfa.hasRole("admin"));
					authz.requestMatchers("/password").access(passwordLastMinute.authenticated());
					authz.requestMatchers("/stronger-password").access(adminsMustMfa);

					authz.anyRequest().authenticated();
				})
				.formLogin(withDefaults())
				.oneTimeTokenLogin(ott -> {
					ott.tokenGenerationSuccessHandler((request, response, oneTimeToken) -> {
						mailNotifier.notify("Log in to demo", "Use token: %s".formatted(oneTimeToken.getTokenValue()),
								"http://localhost:%s/login/ott?token=%s".formatted(serverProperties.getPort(),
										oneTimeToken.getTokenValue()));
						response.sendRedirect("/login/ott");
					});
				})
				.logout(logout -> logout.logoutSuccessUrl("/"))
				.build();
		//@formatter:on
	}

	@Bean
	@Order(1)
	SecurityFilterChain httpBasicChain(HttpSecurity http) {
		return http.securityMatcher("/basic")
			.authorizeHttpRequests(authz -> authz.anyRequest().hasAllRoles("admin", "user"))
			.httpBasic(withDefaults())
			.exceptionHandling(e -> e.accessDeniedHandler(DelegatingMissingAuthorityAccessDeniedHandler.builder()
				.addEntryPointFor(new MissingRoleEntryPoint(), "ROLE_admin")
				.build()))
			.build();
	}

	@Bean
	@Order(2)
	SecurityFilterChain oauth2FilterChain(HttpSecurity http) {
		return http.securityMatcher("/oauth2/**").authorizeHttpRequests(authz -> authz.anyRequest().permitAll())
				.oauth2Client(withDefaults())
				.build();
	}

	@Bean
	DemoUserDetailsService userDetailsService() {
		//@formatter:off
		return new DemoUserDetailsService(
				new DemoUser("josh", "pw", "josh@example.com", List.of("user", "admin")),
				new DemoUser("daniel", "pw", "daniel@example.com", List.of("user"))
		);
		//@formatter:on
	}

}
