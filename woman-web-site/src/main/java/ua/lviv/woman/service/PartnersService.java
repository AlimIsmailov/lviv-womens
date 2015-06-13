package ua.lviv.woman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.woman.entity.Partners;
import ua.lviv.woman.repository.PartnersRepository;

@Service
public class PartnersService {

	@Autowired
	PartnersRepository partnersRepository;

	public void save(Partners partners) {
		partnersRepository.save(partners);
	}

	public List<Partners> findAll() {
		return partnersRepository.findAll();
	}

	public Partners findOneById(int id) {
		return partnersRepository.findOne(id);
	}

	public void delete(Partners partners) {
		partnersRepository.delete(partners);
	}

}
