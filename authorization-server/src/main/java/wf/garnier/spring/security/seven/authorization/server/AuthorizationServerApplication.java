package wf.garnier.spring.security.seven.authorization.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;

@SpringBootApplication
public class AuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerApplication.class, args);
	}

	@Bean
	Customizer<HttpSecurity> httpSecCustomizer() {
		return http -> http.httpBasic(Customizer.withDefaults());
	}

	@Bean
	Customizer<OAuth2AuthorizationServerConfigurer> authzServerConfigurer() {
		return authzServer -> authzServer.oidc(oidc -> oidc.providerConfigurationEndpoint(
				pce -> pce.providerConfigurationCustomizer(config -> config.claim("conference", "Spring I/O 2026"))));
	}

}
