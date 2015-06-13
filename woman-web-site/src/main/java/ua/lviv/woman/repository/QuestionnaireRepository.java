package ua.lviv.woman.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.woman.entity.Questionnaire;

public interface QuestionnaireRepository extends
		JpaRepository<Questionnaire, Integer> {

}
