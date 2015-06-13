package ua.lviv.woman.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.woman.entity.Projects;
import ua.lviv.woman.repository.ProjectsRepository;

@Service
public class ProjectsService {

	@Autowired
	ProjectsRepository projectsRepository;

	public void save(Projects projects) {
		Date date = new Date();
		projects.setPublishedDate(date);
		projectsRepository.save(projects);
	}

	public List<Projects> findAll() {
		return projectsRepository.findAll();
	}

	public Projects finOneById(int id) {
		return projectsRepository.findOne(id);
	}

	public void delete(Projects projects) {
		projectsRepository.delete(projects);
	}

}
