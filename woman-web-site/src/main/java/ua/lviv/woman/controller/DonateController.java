package ua.lviv.woman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DonateController {

	@RequestMapping("/donate")
	private String showDonate() {
		return "donate";
	}

}
