package wf.garnier.spring.security.seven.mfa;

import java.util.List;

import org.springframework.security.oauth2.client.annotation.ClientRegistrationId;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

interface HugoService {

	@GetExchange("http://localhost:8090/hugo/nominees")
	@ClientRegistrationId("default-client")
	List<Book> listHugo(@RequestParam int year);

	record Book(String title, String author) {
	}

}
