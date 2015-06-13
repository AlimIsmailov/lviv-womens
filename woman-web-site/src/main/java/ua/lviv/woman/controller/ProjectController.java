package ua.lviv.woman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.lviv.woman.entity.Projects;
import ua.lviv.woman.service.ProjectsService;

@Controller
public class ProjectController {
	
	private int defaultPageSize = 5;
	private int pageNumber = 0;
	private int defaultPageNumder = 0;
	private boolean isNextPage = false;
	private boolean isPrevPage = false;
	private boolean isPageCounter = false;

	@Autowired
	ProjectsService projectService;

	@ModelAttribute("projects")
	private Projects constructor() {
		return new Projects();
	}

	@RequestMapping("/project")
	private String showProjects(Model model) {
		PagedListHolder<Projects> listHolder = new PagedListHolder<Projects>(projectService.findAll());
		listHolder.setPageSize(defaultPageSize);
		listHolder.setSort(new MutableSortDefinition("publishedDate", true,
				false));
		listHolder.resort();
		if (isPageCounter == true) {
			listHolder.setPage(defaultPageNumder);
		}
		if (isPageCounter == false) {
			listHolder.setPage(pageNumber);
			isPageCounter = true;
		}
		if (isNextPage == true) {
			if (!listHolder.isLastPage()) {
				listHolder.setPage(pageNumber++);
			}
			isNextPage = false;
			isPageCounter = true;
		}
		if (isPrevPage == true) {
			if (!listHolder.isFirstPage()) {
				listHolder.setPage(pageNumber--);
			}
			isPrevPage = false;
			isPageCounter = true;
		}
		model.addAttribute("projectsall", listHolder.getPageList());
		model.addAttribute("allPageCount", listHolder.getPageCount());
		model.addAttribute("currentPageSize", listHolder.getPageSize());
		return "project";
	}
	
	@RequestMapping(value = "/project/{page}", method = RequestMethod.GET)
	private String showEventPaginationByPage(@PathVariable int page) {
		isPageCounter = false;
		this.pageNumber = page;
		return "redirect:/project.html";
	}

	@RequestMapping(value = "/project/next", method = RequestMethod.GET)
	private String nextPageWithEvents() {
		isPageCounter = false;
		isNextPage = true;
		return "redirect:/project.html";
	}

	@RequestMapping(value = "/project/prev", method = RequestMethod.GET)
	private String prevPageWithEvents() {
		isPageCounter = false;
		isPrevPage = true;
		return "redirect:/project.html";
	}

	@RequestMapping("/project/pagesize/{pageSize}")
	private String changePageSize(@PathVariable int pageSize) {
		this.defaultPageSize = pageSize;
		return "redirect:/project.html";
	}
	
	@RequestMapping("/projects/remove/{id}")
	private String removeProjectById(@PathVariable int id) {
		Projects projects = projectService.finOneById(id);
		projectService.delete(projects);
		return "redirect:/project.html";
	}

}
