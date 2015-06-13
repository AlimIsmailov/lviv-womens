package ua.lviv.woman.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.woman.entity.Donors;
import ua.lviv.woman.entity.News;
import ua.lviv.woman.entity.Partners;
import ua.lviv.woman.entity.Projects;
import ua.lviv.woman.entity.Role;
import ua.lviv.woman.entity.Users;
import ua.lviv.woman.repository.DirectionRepository;
import ua.lviv.woman.repository.DonorsReposiory;
import ua.lviv.woman.repository.EventsRepository;
import ua.lviv.woman.repository.NewsRepository;
import ua.lviv.woman.repository.PartnersRepository;
import ua.lviv.woman.repository.ProjectsRepository;
import ua.lviv.woman.repository.RoleRepository;
import ua.lviv.woman.repository.UsersRepository;

@Transactional
@Service
public class InitDbService {
/*
	@Autowired
	UsersRepository usersRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	DirectionRepository directionRepository;

	@Autowired
	NewsRepository newsRepository;

	@Autowired
	EventsRepository eventsRepository;

	@Autowired
	ProjectsRepository projectsRepository;

	@Autowired
	DonorsReposiory donorsRepository;

	@Autowired
	PartnersRepository partnersRepository;

	@PostConstruct
	public void init() {
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);

		Users user = new Users();
		user.setName("admin");
		user.setPassword("admin");
		user.setEmail("example@mail.com");
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		user.setRoles(roles);
		usersRepository.save(user);

		
		 * Direction direction = new Direction();
		 * direction.setName("New direction");
		 * directionRepository.save(direction);
		 

		
		 * Events events = new Events(); events.setName("New event");
		 * events.setPublishedDate(date);
		 * events.setEventInformationUA("Укр язык");
		 * events.setEventInformationEN("Eng lang");
		 * eventsRepository.save(events);
		 

		
		 * Direction direction = new Direction();
		 * direction.setName("Общие вопросы");
		 * directionRepository.save(direction);
		 

		News news = new News();
		Date date = new Date();
		news.setName("New news");
		news.setNewsInfoUA("інформація українською");
		news.setNewsInfoEn("english info");
		news.setPublishedDate(date);
		news.setUser(user);
		newsRepository.save(news);

		Projects projects = new Projects();
		projects.setName("new project");
		projects.setPublishedDate(date);
		projects.setProjectInfo("here some information");
		projectsRepository.save(projects);

		Donors donors = new Donors();
		donors.setName("new doonr");
		donors.setDonorsInfoUA("here is some info");
		donors.setLink("example@mail.ru");
		donorsRepository.save(donors);

		Partners partners = new Partners();
		partners.setName("new partner");
		partners.setLink("some link");
		partners.setPartnersInfoUA("some partners info");
		partnersRepository.save(partners);
	}*/
}
