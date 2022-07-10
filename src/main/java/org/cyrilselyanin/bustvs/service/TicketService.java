package org.cyrilselyanin.bustvs.service;

import org.cyrilselyanin.bustvs.domain.BusTrip;
import org.cyrilselyanin.bustvs.domain.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    Ticket create(Ticket ticket);

    Optional<Ticket> getTicket(Long ticketId);
    List<Ticket> getAllTickets();

    Ticket update(Ticket ticket, Long id);
    void delete(Long id);

    Optional<BusTrip> getTicketBusTrip(Long ticketId);

    void regCash(Ticket ticket);
}
