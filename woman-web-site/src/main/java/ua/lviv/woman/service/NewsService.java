package ua.lviv.woman.service;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.woman.entity.News;
import ua.lviv.woman.repository.NewsRepository;
import ua.lviv.woman.repository.UsersRepository;

@Service
public class NewsService {

	@Autowired
	NewsRepository newsRepository;

	@Autowired
	UsersRepository usersRepository;

	public void save(News news, Principal principal) {
		Date date = new Date();
		news.setUser(usersRepository.findOneByName(principal.getName()));
		news.setPublishedDate(date);
		newsRepository.save(news);
	}

	public List<News> findAll() {
		return newsRepository.findAll();
	}

	public News findOneByid(int id) {
		return newsRepository.findOne(id);
	}

	public News update(News news) {
		return newsRepository.save(news);
	}

	public List<News> findAllToArchive() {
		return newsRepository.findAll();
	}

}
