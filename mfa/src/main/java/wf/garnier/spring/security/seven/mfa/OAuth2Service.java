package wf.garnier.spring.security.seven.mfa;

import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import org.springframework.http.MediaType;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.client.OAuth2ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import static org.springframework.security.oauth2.client.web.client.RequestAttributeClientRegistrationIdResolver.clientRegistrationId;

@Service
class OAuth2Service {

	private final RestClient restClient;

	OAuth2Service(RestClient.Builder restClientBuilder, OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager,
			OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository) {
		var interceptor = new OAuth2ClientHttpRequestInterceptor(oAuth2AuthorizedClientManager);
		interceptor.setAuthorizationFailureHandler(
				OAuth2ClientHttpRequestInterceptor.authorizationFailureHandler(oAuth2AuthorizedClientRepository));
		this.restClient = restClientBuilder.requestInterceptor(interceptor).build();
	}

	@Retryable(maxRetries = 1)
	ClientRegistrationResponse register() {
		return restClient.post()
			.uri("http://localhost:9000/oauth2/register")
			.contentType(MediaType.APPLICATION_JSON)
			.attributes(clientRegistrationId("admin-client"))
			.body("""
					{
						"grant_types": ["authorization_code", "client_credentials"],
						"redirect_uris": ["http://localhost:8080/authorize/oauth2/code"]
					}
					""")
			.retrieve()
			.body(ClientRegistrationResponse.class);

	}

	@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
	record ClientRegistrationResponse(String clientId, String clientSecret) {
	}

}
