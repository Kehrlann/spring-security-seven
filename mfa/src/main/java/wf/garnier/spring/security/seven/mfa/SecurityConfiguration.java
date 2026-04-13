package wf.garnier.spring.security.seven.mfa;

import java.util.List;

import wf.garnier.spring.security.seven.mfa.ott.MailNotifier;
import wf.garnier.spring.security.seven.mfa.user.DemoUser;
import wf.garnier.spring.security.seven.mfa.user.DemoUserDetailsService;

import org.springframework.boot.web.server.autoconfigure.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
class SecurityConfiguration {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, MailNotifier mailNotifier,
			ServerProperties serverProperties) {

		//@formatter:off
		return http
				.authorizeHttpRequests(authz -> {
					authz.requestMatchers("/", "/css/**", "/favicon.ico", "/favicon.svg", "/error").permitAll();
					authz.requestMatchers("/admin").hasRole("admin");
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
	DemoUserDetailsService userDetailsService() {
		//@formatter:off
		return new DemoUserDetailsService(
				new DemoUser("josh", "pw", "josh@example.com", List.of("user", "admin")),
				new DemoUser("daniel", "pw", "daniel@example.com", List.of("user"))
		);
		//@formatter:on
	}

}
