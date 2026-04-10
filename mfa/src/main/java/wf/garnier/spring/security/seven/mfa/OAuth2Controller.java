package wf.garnier.spring.security.seven.mfa;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class OAuth2Controller {

	private final OAuth2Service oAuth2Service;

	OAuth2Controller(OAuth2Service oAuth2Service) {
		this.oAuth2Service = oAuth2Service;
	}

	@GetMapping("/oauth2/hugo")
	public List<String> hugo() {
		return List.of();
	}

	@GetMapping("/oauth2/register")
	public OAuth2Service.ClientRegistrationResponse registration() {
		return oAuth2Service.register();
	}

}
