package com.cyrilselyanin.bustvm.helper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.cyrilselyanin.bustvm.controller.TicketController;
import com.cyrilselyanin.bustvm.domain.BusTrip;
import com.cyrilselyanin.bustvm.domain.Ticket;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class BusTripModelAssembler implements SimpleRepresentationModelAssembler<BusTrip> {
    @Override
    public void addLinks(EntityModel<BusTrip> resource) {
        if (resource.getContent() == null) return;
//        Long ticketId = resource.getContent().getId();
        Long busTripId = resource.getContent().getId();
        var ticket = resource.getContent().getTickets()
                .stream()
                .filter(t -> t.getBusTrip().getId().equals(busTripId))
                .findFirst();

        if (ticket.isEmpty()) return;
//        if (busTripId == null) return;

        resource.add(
                linkTo(
                        methodOn(
                                TicketController.class
                        ).getTicketBusTrip(ticket.get().getId())
                ).withSelfRel()
        );
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<BusTrip>> resources) {
        //
    }
}
