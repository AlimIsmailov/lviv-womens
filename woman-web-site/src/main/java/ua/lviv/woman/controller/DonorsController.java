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

import ua.lviv.woman.entity.Donors;
import ua.lviv.woman.service.DonorsService;

@Controller
public class DonorsController {
	
	private int defaultPageSize = 5;
	private int pageNumber = 0;
	private int defaultPageNumder = 0;
	private boolean isNextPage = false;
	private boolean isPrevPage = false;
	private boolean isPageCounter = false;

	@Autowired
	DonorsService donorsService;

	@ModelAttribute("donors")
	private Donors constructor() {
		return new Donors();
	}

	@RequestMapping("/donors")
	private String showDonors(Model model) {
		PagedListHolder<Donors> listHolder = new PagedListHolder<Donors>(donorsService.findAll());
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
		model.addAttribute("donorsall", listHolder.getPageList());
		model.addAttribute("allPageCount", listHolder.getPageCount());
		model.addAttribute("currentPageSize", listHolder.getPageSize());
		return "donors";
	}
	
	@RequestMapping(value = "/donors/{page}", method = RequestMethod.GET)
	private String showEventPaginationByPage(@PathVariable int page) {
		isPageCounter = false;
		this.pageNumber = page;
		return "redirect:/donors.html";
	}

	@RequestMapping(value = "/donors/next", method = RequestMethod.GET)
	private String nextPageWithEvents() {
		isPageCounter = false;
		isNextPage = true;
		return "redirect:/donors.html";
	}

	@RequestMapping(value = "/donors/prev", method = RequestMethod.GET)
	private String prevPageWithEvents() {
		isPageCounter = false;
		isPrevPage = true;
		return "redirect:/donors.html";
	}

	@RequestMapping("/donors/pagesize/{pageSize}")
	private String changePageSize(@PathVariable int pageSize) {
		this.defaultPageSize = pageSize;
		return "redirect:/donors.html";
	}

}
