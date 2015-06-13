package ua.lviv.woman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.lviv.woman.entity.Events;
import ua.lviv.woman.entity.News;
import ua.lviv.woman.entity.Partners;
import ua.lviv.woman.service.EventsService;
import ua.lviv.woman.service.NewsService;
import ua.lviv.woman.service.PartnersService;

@Controller
public class HomePageController {

	private static final int DEFAULT_PAGE_SIZE = 3;

	@Autowired
	PartnersService partnersService;

	@Autowired
	NewsService newsService;

	@Autowired
	EventsService eventsService;

	@RequestMapping("/home_page")
	public String index(Model model) {
		PagedListHolder<Partners> partnersListHolder = new PagedListHolder<Partners>(
				partnersService.findAll());
		partnersListHolder.setPageSize(DEFAULT_PAGE_SIZE);
		model.addAttribute("partnersall", partnersListHolder.getPageList());
		PagedListHolder<Events> eventsListHolder = new PagedListHolder<Events>(
				eventsService.findAll());
		eventsListHolder.setPageSize(DEFAULT_PAGE_SIZE);
		eventsListHolder.setSort(new MutableSortDefinition("topOfEvents", true,
				false));
		eventsListHolder.resort();
		model.addAttribute("eventsall", eventsListHolder.getPageList());
		PagedListHolder<News> newsListHolder = new PagedListHolder<News>(
				newsService.findAll());
		newsListHolder.setPageSize(DEFAULT_PAGE_SIZE);
		newsListHolder.setSort(new MutableSortDefinition("topOfNews", true,
				false));
		newsListHolder.resort();
		model.addAttribute("newsall", newsListHolder.getPageList());
		return "home_page";
	}

}
