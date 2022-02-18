package com.cyrilselyanin.bustvm.repository;

import com.cyrilselyanin.bustvm.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
