package org.cyrilselyanin.bustvs.service;

import org.cyrilselyanin.bustvs.domain.Ticket;
import org.cyrilselyanin.bustvs.dto.TicketDto;

public interface CashRegisterService {
    void regCash(Ticket ticket);
}
