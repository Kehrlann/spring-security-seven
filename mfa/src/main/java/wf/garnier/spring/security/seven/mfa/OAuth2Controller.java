package wf.garnier.spring.security.seven.mfa;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class OAuth2Controller {

	private final OAuth2Service oAuth2Service;

	private final HugoService hugoService;

	OAuth2Controller(OAuth2Service oAuth2Service, HugoService hugoService) {
		this.oAuth2Service = oAuth2Service;
		this.hugoService = hugoService;
	}

	@GetMapping("/oauth2/hugo")
	public List<HugoService.Book> hugo() {
		return hugoService.listHugo(2026);
	}

	@GetMapping("/oauth2/register")
	public OAuth2Service.ClientRegistrationResponse registration() {
		return oAuth2Service.register();
	}

}
