package ru.otus.buyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.buyer.model.TicketModel;

public interface TicketRepository extends JpaRepository<TicketModel, Long> {
}
