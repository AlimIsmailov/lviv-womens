package ua.lviv.woman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.lviv.woman.entity.Events;
import ua.lviv.woman.entity.News;
import ua.lviv.woman.service.EventsService;
import ua.lviv.woman.service.NewsService;

@Controller
public class ArchiveController {

	@Autowired
	private NewsService newsService;

	@Autowired
	private EventsService eventsService;

	private int defaultPageSizeToEvents = 5;
	private int defaultPageSizeToNews = 5;
	private int increment = 5;

	@RequestMapping(value = "/archive", method = RequestMethod.GET)
	private String showArchivePage(Model model) {
		PagedListHolder<Events> listHolder = new PagedListHolder<Events>(
				eventsService.findAll());
		listHolder.setPageSize(defaultPageSizeToEvents);
		PagedListHolder<News> listHolder2 = new PagedListHolder<News>(
				newsService.findAll());
		listHolder2.setPage(defaultPageSizeToNews);
		model.addAttribute("eventstoarchive", listHolder.getPageList());
		model.addAttribute("newstoarchive", listHolder2.getPageList());
		return "archive";
	}

	@RequestMapping(value = "/archive/moreevents", method = RequestMethod.GET)
	private String showMoreEvents() {
		this.defaultPageSizeToEvents = defaultPageSizeToEvents + increment;
		return "redirect:/archive.html";
	}

	@RequestMapping(value = "/archive/morenews", method = RequestMethod.GET)
	private String showMoreNews() {
		this.defaultPageSizeToNews = defaultPageSizeToNews + increment;
		return "redirect:/archive.html";
	}

}
