package ua.lviv.woman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.woman.entity.Direction;
import ua.lviv.woman.repository.DirectionRepository;

@Service
public class DirectionService {

	@Autowired
	DirectionRepository directionRepository;

	public List<Direction> findAll() {
		return directionRepository.findAll();
	}

	public void save(Direction direction) {
		directionRepository.save(direction);
	}

}
