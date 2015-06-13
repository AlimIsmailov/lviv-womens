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
public class AlbumController {

	@Autowired
	private EventsService eventsService;

	@Autowired
	private NewsService newsService;

	private int defaultPageSizeToEvents = 20;
	private int defaultPageSizeToNews = 20;
	private int increment = 20;

	@RequestMapping(value = "/album", method = RequestMethod.GET)
	private String shoeAlbumPage(Model model) {
		PagedListHolder<Events> listHolder = new PagedListHolder<Events>(
				eventsService.findAll());
		listHolder.setPageSize(defaultPageSizeToEvents);
		PagedListHolder<News> listHolder2 = new PagedListHolder<News>(
				newsService.findAll());
		listHolder2.setPage(defaultPageSizeToNews);
		model.addAttribute("alleventsinalbum", listHolder.getPageList());
		model.addAttribute("allnewsinalbum", listHolder2.getPageList());
		return "album";
	}

	@RequestMapping(value = "/album/showmoreevents", method = RequestMethod.GET)
	private String showMoreEventsPhotos() {
		this.defaultPageSizeToEvents = defaultPageSizeToEvents + increment;
		return "redirect:/album.html";
	}

	@RequestMapping(value = "/album/showmorenews", method = RequestMethod.GET)
	private String showMoreNewsPhotos() {
		this.defaultPageSizeToNews = defaultPageSizeToNews + increment;
		return "redirect:/album.html";
	}

}
