package com.cyrilselyanin.bustvm.validation.beforedate;

import com.cyrilselyanin.bustvm.domain.Ticket;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TicketBeforeDateValidator implements ConstraintValidator<ValidateBeforeDate, Ticket> {

    @Override
    public void initialize(ValidateBeforeDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(Ticket ticket, ConstraintValidatorContext context) {
        if (ticket == null) {
            return true;
        }

        return ticket.getDepartureDateTime().compareTo(
                ticket.getArrivalDatetime()
        ) < 0;
    }
}