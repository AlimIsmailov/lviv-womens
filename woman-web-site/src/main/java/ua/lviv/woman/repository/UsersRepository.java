package ua.lviv.woman.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.woman.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {

	Users findOneByName(String name);

}
