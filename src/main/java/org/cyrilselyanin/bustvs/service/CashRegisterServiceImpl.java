package org.cyrilselyanin.bustvs.service;

import org.cyrilselyanin.bustvs.domain.Ticket;
import org.cyrilselyanin.bustvs.dto.TicketDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * Cash register communication service.
 * It sends via rabbitMQ a dto to the cashreg subsystem.
 */
@Service
public class CashRegisterServiceImpl implements CashRegisterService {
    private final RabbitTemplate rabbitTemplate;

    public CashRegisterServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Got ticket, adapt it and send for registering
     * @param ticket Ticket instance
     */
    @Override
    public void regCash(Ticket ticket) {
        TicketDtoAdapter ticketDtoAdapter = new TicketDtoAdapter();
        TicketDto ticketDto = ticketDtoAdapter.adapt(ticket);
        // rabbit
        rabbitTemplate.convertAndSend(ticketDto);
    }

    /**
     * Ticket dto adapter
     */
    public static class TicketDtoAdapter {
        public TicketDto adapt(Ticket ticket) {
            TicketDto ticketDto = new TicketDto();
            ticketDto.setPassengerLastname(ticket.getPassengerLastname());
            ticketDto.setPassengerFirstname(ticket.getPassengerFirstname());
            ticketDto.setPassengerMiddlename(ticket.getPassengerMiddlename());
            ticketDto.setBusRouteNumber(ticket.getBusRouteNumber());
            ticketDto.setDepartureBuspointName(ticket.getDepartureBuspointName());
            ticketDto.setDepartureDateTime(ticket.getDepartureDateTime());
            ticketDto.setPrice(ticket.getPrice());
            return ticketDto;
        }
    }
}
