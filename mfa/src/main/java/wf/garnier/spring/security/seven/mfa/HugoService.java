package wf.garnier.spring.security.seven.mfa;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class HugoService {

    List<Book> getNominees(int year) {
        // TODO: use rest client
        return Collections.emptyList();
    }

    record Book(String title, String author) {

	}

}
