package org.cyrilselyanin.bustvs.service;

import org.cyrilselyanin.bustvs.domain.Ticket;
import org.cyrilselyanin.bustvs.dto.TicketDto;
import org.springframework.stereotype.Service;

@Service
public class CashRegisterServiceImpl implements CashRegisterService {

    @Override
    public void regCash(Ticket ticket) {
        TicketDtoAdapter ticketDtoAdapter = new TicketDtoAdapter();
        TicketDto ticketDto = ticketDtoAdapter.adapt(ticket);
        //
    }

    public class TicketDtoAdapter {
        public TicketDto adapt(Ticket ticket) {
            //
        }
    }
}
