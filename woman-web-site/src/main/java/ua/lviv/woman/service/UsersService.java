package ua.lviv.woman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.woman.entity.Users;
import ua.lviv.woman.repository.UsersRepository;

@Service
public class UsersService {

	@Autowired
	UsersRepository usersRepository;

	public List<Users> getAllUsers() {
		return usersRepository.findAll();
	}

}
