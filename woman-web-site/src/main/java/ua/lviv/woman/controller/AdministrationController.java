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

import ua.lviv.woman.entity.Partners;
import ua.lviv.woman.service.PartnersService;

@Controller
public class AdministrationController {
	
	private int defaultPageSize = 5;
	private int pageNumber = 0;
	private int defaultPageNumder = 0;
	private boolean isNextPage = false;
	private boolean isPrevPage = false;
	private boolean isPageCounter = false;

	@Autowired
	private PartnersService partnersService;

	@ModelAttribute("partners")
	private Partners constructor() {
		return new Partners();
	}

	@RequestMapping("/administration")
	private String showAdminPage(Model model) {
		PagedListHolder<Partners> listHolder = new PagedListHolder<Partners>(partnersService.findAll());
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
		model.addAttribute("allpartners", listHolder.getPageList());
		model.addAttribute("allPageCount", listHolder.getPageCount());
		return "administration";
	}
	
	@RequestMapping(value = "/administration/{page}", method = RequestMethod.GET)
	private String showEventPaginationByPage(@PathVariable int page) {
		isPageCounter = false;
		this.pageNumber = page;
		return "redirect:/administration.html";
	}

	@RequestMapping(value = "/administration/next", method = RequestMethod.GET)
	private String nextPageWithEvents() {
		isPageCounter = false;
		isNextPage = true;
		return "redirect:/administration.html";
	}

	@RequestMapping(value = "/administration/prev", method = RequestMethod.GET)
	private String prevPageWithEvents() {
		isPageCounter = false;
		isPrevPage = true;
		return "redirect:/administration.html";
	}

	@RequestMapping("/administration/pagesize/{pageSize}")
	private String changePageSize(@PathVariable int pageSize) {
		this.defaultPageSize = pageSize;
		return "redirect:/administration.html";
	}

	@RequestMapping(value = "/partners/remove/{id}")
	private String deletePartner(@PathVariable int id) {
		Partners partners = partnersService.findOneById(id);
		partnersService.delete(partners);
		return "redirect:/administration.html";
	}

	@RequestMapping(value = "/partners/edit/{id}")
	private String editPartner(@PathVariable int id) {
		return "redirect:/administration.html";
	}

}
