package com.cyrilselyanin.bustvm.repository;

import com.cyrilselyanin.bustvm.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}