package wf.garnier.spring.security.seven.mfa;

import wf.garnier.spring.security.seven.mfa.user.DemoUserDetailsService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class MfaController {

	private final DemoUserDetailsService userDetailsService;

	public MfaController(DemoUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

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

	@GetMapping("/daniel")
	public String getDaniel() {
		return "daniel";
	}

	@GetMapping("/password")
	public String passwordPage() {
		return "password";
	}

	@GetMapping("/admin")
	public String adminPage() {
		return "admin";
	}

	@PostMapping("/password")
	public String updatePassword(Authentication authentication, String newPassword, Model model) {
		userDetailsService.updatePassword(authentication.getName(), newPassword);
		model.addAttribute("success", true);
		return "redirect:/private";
	}

	@GetMapping("/basic")
	@ResponseBody
	public HttpBasicResponse theQuestions() {
		return new HttpBasicResponse("Life, the Universe, and Everything", 42);
	}

	record HttpBasicResponse(String question, int response) {

	}

}
