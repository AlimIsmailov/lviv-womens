package ua.lviv.woman.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.woman.entity.Projects;

public interface ProjectsRepository extends JpaRepository<Projects, Integer> {

}
