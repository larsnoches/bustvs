package org.cyrilselyanin.bustvs.service;

import org.cyrilselyanin.bustvs.domain.Ticket;
import org.cyrilselyanin.bustvs.dto.TicketDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class CashRegisterServiceImplTest {

    @Test
    void adaptTicketToDto_thenCorrect() {
        Ticket ticket = new Ticket(
                1L,
                Timestamp.valueOf("2022-07-07 08:09:21.61"),
                "Petrov",
                "Ivan",
                "Ivanovich",
                "105",
                "",
                "1a",
                "What Bus",
                "10km",
                "KP",
                Timestamp.valueOf("2022-07-07 09:09:21.61"),
                Timestamp.valueOf("2022-07-07 10:09:21.61"),
                BigDecimal.valueOf(500),
                null,
                "123id"
        );
        CashRegisterServiceImpl.TicketDtoAdapter ticketDtoAdapter = new CashRegisterServiceImpl.TicketDtoAdapter();
        TicketDto ticketDto = ticketDtoAdapter.adapt(ticket);
        assertNotNull(ticketDto);
    }
}