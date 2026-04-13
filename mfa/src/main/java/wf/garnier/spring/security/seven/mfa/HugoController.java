package wf.garnier.spring.security.seven.mfa;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HugoController {

    private final HugoService hugoService;

    HugoController(HugoService hugoService) {
        this.hugoService = hugoService;
    }

    @GetMapping("/oauth2/hugo")
    public List<HugoService.Book> books() {
        return hugoService.getNominees(2022);
    }
}
