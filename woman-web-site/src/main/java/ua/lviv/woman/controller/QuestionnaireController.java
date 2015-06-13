package ua.lviv.woman.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.lviv.woman.entity.Questionnaire;
import ua.lviv.woman.service.DirectionService;
import ua.lviv.woman.service.QuestionnaireService;

@Controller
public class QuestionnaireController {

	@Autowired
	QuestionnaireService questionnaireService;

	@Autowired
	DirectionService directionService;

	@ModelAttribute("questionnaire")
	private Questionnaire constructor() {
		return new Questionnaire();
	}

	@RequestMapping("/questionnaire")
	private String showQuestionnaire(Model model) {
		model.addAttribute("directions", directionService.findAll());
		return "questionnaire";
	}

	@RequestMapping(value = "/questionnaire", method = RequestMethod.POST)
	private String fillTheForm(
			@Valid @ModelAttribute("questionnaire") Questionnaire questionnaire,
			BindingResult result) {
		if (result.hasErrors()) {
			return "questionnaire";
		}
		questionnaireService.save(questionnaire);
		return "redirect:/questionnaire.html?success=true";
	}

}
