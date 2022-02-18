package com.cyrilselyanin.bustvm.validation;

import com.cyrilselyanin.bustvm.domain.Ticket;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TicketBeforeDateValidator implements ConstraintValidator<ValidaBeforeDate, Ticket> {

    @Override
    public void initialize(ValidaBeforeDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(Ticket ticket, ConstraintValidatorContext context) {
        if (ticket == null) {
            return true;
        }

        return ticket.getArrivalDatetime().compareTo(
                ticket.getDepartureDateTime()
        ) < 0;
    }
}
