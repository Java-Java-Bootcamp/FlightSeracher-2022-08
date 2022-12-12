package ru.otus.buyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.buyer.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
