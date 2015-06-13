package ua.lviv.woman.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.lviv.woman.entity.Events;
import ua.lviv.woman.service.EventsService;

@Controller
public class EventController {

	private static final byte DEFAULT_TO_TOP_VALUE = 0;
	private static final byte SET_TO_TOP_VALUE = 1;
	private static final byte PUT_TO_ARCHIVE_VALUE = 1;

	private int defaultPageSize = 5;
	private int pageNumber = 0;
	private int defaultPageNumder = 0;
	private boolean isNextPage = false;
	private boolean isPrevPage = false;
	private boolean isPageCounter = false;
	private String sortingVariable = "publishedDate";

	@Autowired
	EventsService eventsService;

	@ModelAttribute("events")
	public Events constructor() {
		return new Events();
	}

	@RequestMapping(value = "/event", method = RequestMethod.GET)
	private String showEvent(Model model) {
		PagedListHolder<Events> listHolder = new PagedListHolder<Events>(
				eventsService.findAll());
		listHolder.setPageSize(defaultPageSize);
		listHolder.setSort(new MutableSortDefinition(sortingVariable, true,
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
		model.addAttribute("eventsall", listHolder.getPageList());
		model.addAttribute("allPageCount", listHolder.getPageCount());
		model.addAttribute("currentPageSize", listHolder.getPageSize());
		return "event";
	}

	@RequestMapping(value = "/event/sort/{sort}", method = RequestMethod.GET)
	private String sortEventPage(@PathVariable String sort) {
		this.sortingVariable = sort;
		return "redirect:/event.html";
	}

	@RequestMapping(value = "/event/{page}", method = RequestMethod.GET)
	private String showEventPaginationByPage(@PathVariable int page) {
		isPageCounter = false;
		this.pageNumber = page;
		return "redirect:/event.html";
	}

	@RequestMapping(value = "/event/next", method = RequestMethod.GET)
	private String nextPageWithEvents() {
		isPageCounter = false;
		isNextPage = true;
		return "redirect:/event.html";
	}

	@RequestMapping(value = "/event/prev", method = RequestMethod.GET)
	private String prevPageWithEvents() {
		isPageCounter = false;
		isPrevPage = true;
		return "redirect:/event.html";
	}

	@RequestMapping("/event/pagesize/{pageSize}")
	private String changePageSize(@PathVariable int pageSize) {
		this.defaultPageSize = pageSize;
		return "redirect:/event.html";
	}

	@RequestMapping(value = "/event/remove/{id}")
	public String removeEvent(@PathVariable int id) {
		Events event = eventsService.findOneById(id);
		event.setToArchive(PUT_TO_ARCHIVE_VALUE);
		event.setTopOfEvents(DEFAULT_TO_TOP_VALUE);
		eventsService.update(event);
		return "redirect:/event.html";
	}

	@RequestMapping(value = "/event/edit/{id}")
	public String showEventProfileById(@PathVariable int id, Model model,
			@Valid @ModelAttribute("editevent") Events events,
			BindingResult result) {
		model.addAttribute("event", eventsService.findOneById(id));
		return "eventprofile";
	}

	@RequestMapping(value = "/event/edit/{id}", method = RequestMethod.POST)
	public String editEvent(@Valid @ModelAttribute("editevent") Events events,
			BindingResult result, @PathVariable int id) {
		if (result.hasErrors()) {
			return "eventprofile";
		}
		eventsService.update(events);
		return "redirect:/event.html";
	}

	@RequestMapping(value = "/event/totop/{id}")
	public String toTop(@PathVariable int id) {
		Events event = eventsService.findOneById(id);
		event.setTopOfEvents(SET_TO_TOP_VALUE);
		eventsService.update(event);
		return "redirect:/event.html";
	}

}
