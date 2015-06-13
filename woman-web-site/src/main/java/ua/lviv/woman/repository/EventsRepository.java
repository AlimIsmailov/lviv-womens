package ua.lviv.woman.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.woman.entity.Events;

public interface EventsRepository extends JpaRepository<Events, Integer> {

}
