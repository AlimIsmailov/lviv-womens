package ua.lviv.woman.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.woman.entity.Questionnaire;
import ua.lviv.woman.repository.QuestionnaireRepository;

@Service
public class QuestionnaireService {

	@Autowired
	QuestionnaireRepository questionnaireRepository;

	public void save(Questionnaire questionnaire) {
		Date date = new Date();
		questionnaire.setCreationDate(date);
		questionnaireRepository.save(questionnaire);
	}

}
