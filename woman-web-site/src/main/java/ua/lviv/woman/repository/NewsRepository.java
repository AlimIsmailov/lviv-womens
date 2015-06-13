package ua.lviv.woman.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.woman.entity.News;

public interface NewsRepository extends JpaRepository<News, Integer> {

}
