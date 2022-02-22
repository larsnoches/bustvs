package com.cyrilselyanin.bustvm.service;

import com.cyrilselyanin.bustvm.domain.BusTrip;
import com.cyrilselyanin.bustvm.domain.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    Ticket create(Ticket ticket);

    Optional<Ticket> getTicket(Long ticketId);
    List<Ticket> getAllTickets();

    Ticket update(Ticket ticket, Long id);
    void delete(Long id);

    Optional<BusTrip> getTicketBusTrip(Long ticketId);
}
