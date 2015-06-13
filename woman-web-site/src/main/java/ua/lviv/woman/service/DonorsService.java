package ua.lviv.woman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.woman.entity.Donors;
import ua.lviv.woman.repository.DonorsReposiory;

@Service
public class DonorsService {

	@Autowired
	DonorsReposiory donorsReposiory;

	public void save(Donors donors) {
		donorsReposiory.save(donors);
	}

	public List<Donors> findAll() {
		return donorsReposiory.findAll();
	}

}
