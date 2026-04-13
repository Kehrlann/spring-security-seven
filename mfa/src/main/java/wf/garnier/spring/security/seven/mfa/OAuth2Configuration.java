package wf.garnier.spring.security.seven.mfa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.resilience.annotation.EnableResilientMethods;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.client.OAuth2ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.web.client.support.OAuth2RestClientHttpServiceGroupConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableResilientMethods
class OAuth2Configuration {


    @Bean
    @Order(2)
    SecurityFilterChain oauth2FilterChain(HttpSecurity http) {
        return http.securityMatcher("/oauth2/**", "/authorize/oauth2/code/**")
                .authorizeHttpRequests(authz -> authz.anyRequest().authenticated())
                .oauth2Client(withDefaults())
                .sessionManagement(withDefaults())
                .build();
    }

    @Bean
    OAuth2RestClientHttpServiceGroupConfigurer oauth2ServiceClientSupport(OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager) {
        return OAuth2RestClientHttpServiceGroupConfigurer.from(oAuth2AuthorizedClientManager);
    }
}
