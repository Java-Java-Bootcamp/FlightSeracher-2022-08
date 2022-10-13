package ru.otus.flightsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.flightsearch.entity.User;

@Repository
public interface ServiceUserRepository extends JpaRepository<User, Long> {
    User findById(long id);
}