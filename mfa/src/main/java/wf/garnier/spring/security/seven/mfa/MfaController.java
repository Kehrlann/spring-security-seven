package wf.garnier.spring.security.seven.mfa;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class MfaController {

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/private")
    public String privatePage(Authentication authentication, Model model) {
        model.addAttribute("name", authentication.getName());
        model.addAttribute("authType", authentication.getClass().getSimpleName());
        model.addAttribute("authorities", authentication.getAuthorities());
        return "private";
    }
}
