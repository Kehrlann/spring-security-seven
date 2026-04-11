package wf.garnier.spring.security.seven.mfa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.service.registry.ImportHttpServices;

@SpringBootApplication
@ImportHttpServices(HugoService.class)
public class MfaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MfaApplication.class, args);
	}

}
