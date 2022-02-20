package com.cyrilselyanin.bustvm.helper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.cyrilselyanin.bustvm.controller.TicketController;
import com.cyrilselyanin.bustvm.domain.Ticket;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class TicketModelAssembler implements SimpleRepresentationModelAssembler<Ticket> {
    @Override
    public void addLinks(EntityModel<Ticket> resource) {
        if (resource.getContent() == null) return;
        Long ticketId = resource.getContent().getId();
        if (ticketId == null) return;
        resource.add(
                linkTo(
                        methodOn(
                                TicketController.class
                        ).getTicketById(ticketId)
                ).withSelfRel()
        );
        resource.add(
                linkTo(
                        methodOn(
                                TicketController.class
                        ).getTicketBusTrip(ticketId)
                ).withRel("busTrip")
        );
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Ticket>> resources) {
        resources.add(
                linkTo(
                        methodOn(
                                TicketController.class
                        ).getAllTickets()
                ).withSelfRel()
        );
    }
}

//public class TicketModelAssembler
//        implements RepresentationModelAssembler<Ticket, EntityModel<Ticket>> {
//    @Override
//    public EntityModel<Ticket> toModel(Ticket ticket) {
//        return EntityModel.of(
//                ticket,
//                linkTo(
//                        methodOn(TicketController.class).one(
//                                ticket.getId()
//                        )
//                ).withSelfRel(),
//                linkTo(
//                        methodOn(TicketController.class).all()
//                ).withRel("tickets")
//        );
//    }
//}