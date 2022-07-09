package org.cyrilselyanin.bustvs.validation.notfound;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(Long id) {
        super("Couldn't find ticket {}".formatted(id));
    }
}
