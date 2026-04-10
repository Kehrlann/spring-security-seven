package wf.garnier.spring.security.seven.authorization.server;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HugoAwardsController {

	private final HugoAwardsService hugoAwardsService;

	HugoAwardsController(HugoAwardsService hugoAwardsService) {
		this.hugoAwardsService = hugoAwardsService;
	}

	@GetMapping("/hugo/winners")
	public List<Book> getWinners(@RequestParam(required = false) String from,
			@RequestParam(required = false) String to) {
		return hugoAwardsService.getWinner(from, to);
	}

}
