package ua.lviv.woman.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.woman.entity.Events;
import ua.lviv.woman.repository.DirectionRepository;
import ua.lviv.woman.repository.EventsRepository;

@Service
@Transactional
public class EventsService {

	@Autowired
	EventsRepository eventsRepository;

	@Autowired
	DirectionRepository directionRepository;

	public void save(Events events) {
		Date date = new Date();
		events.setPublishedDate(date);
		eventsRepository.save(events);
	}

	public List<Events> findAll() {
		return eventsRepository.findAll();
	}

	public void delete(Events event) {
		eventsRepository.delete(event);
	}

	public Events findOneById(int id) {
		return eventsRepository.findOne(id);
	}

	public void update(Events event) {
		eventsRepository.save(event);
	}

	public List<Events> findAllToArchive() {
		return eventsRepository.findAll();
	}

}
