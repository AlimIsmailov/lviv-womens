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

import ua.lviv.woman.entity.News;
import ua.lviv.woman.service.NewsService;

@Controller
public class NewsController {

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
	NewsService newsService;

	@ModelAttribute("news")
	private News constructor() {
		return new News();
	}

	@RequestMapping("/news")
	public String showNewsPage(Model model) {
		PagedListHolder<News> listHolder = new PagedListHolder<News>(
				newsService.findAll());
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
		model.addAttribute("newsall", listHolder.getPageList());
		model.addAttribute("allPageCount", listHolder.getPageCount());
		model.addAttribute("currentPageSize", listHolder.getPageSize());
		return "news";
	}

	@RequestMapping(value = "/news/sort/{sort}", method = RequestMethod.GET)
	private String sortNewsPage(@PathVariable String sort) {
		this.sortingVariable = sort;
		return "redirect:/news.html";
	}

	@RequestMapping(value = "/news/{page}", method = RequestMethod.GET)
	private String showEventPaginationByPage(@PathVariable int page) {
		isPageCounter = false;
		this.pageNumber = page;
		return "redirect:/news.html";
	}

	@RequestMapping(value = "/news/next", method = RequestMethod.GET)
	private String nextPageWithEvents() {
		isPageCounter = false;
		isNextPage = true;
		return "redirect:/news.html";
	}

	@RequestMapping(value = "/news/prev", method = RequestMethod.GET)
	private String prevPageWithEvents() {
		isPageCounter = false;
		isPrevPage = true;
		return "redirect:/news.html";
	}

	@RequestMapping("/news/pagesize/{pageSize}")
	private String changePageSize(@PathVariable int pageSize) {
		this.defaultPageSize = pageSize;
		return "redirect:/news.html";
	}

	@RequestMapping(value = "/news/remove/{id}")
	private String deleteNews(@PathVariable int id) {
		News news = newsService.findOneByid(id);
		news.setToArchive(PUT_TO_ARCHIVE_VALUE);
		news.setTopOfNews(DEFAULT_TO_TOP_VALUE);
		newsService.update(news);
		return "redirect:/news.html";
	}

	@RequestMapping(value = "/news/edit/{id}")
	private String showNewsprofileById(@PathVariable int id, Model model,
			@Valid @ModelAttribute("editnews") News news, BindingResult result) {
		model.addAttribute("news", newsService.findOneByid(id));
		return "newsprofile";
	}

	@RequestMapping(value = "/news/totop/{id}")
	private String setToTopNews(@PathVariable int id) {
		News news = newsService.findOneByid(id);
		news.setTopOfNews(SET_TO_TOP_VALUE);
		newsService.update(news);
		return "redirect:/news.html";
	}

}
