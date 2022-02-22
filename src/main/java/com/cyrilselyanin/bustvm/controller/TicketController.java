package com.cyrilselyanin.bustvm.controller;

import com.cyrilselyanin.bustvm.domain.BusTrip;
import com.cyrilselyanin.bustvm.domain.Ticket;
import com.cyrilselyanin.bustvm.helper.BusTripModelAssembler;
import com.cyrilselyanin.bustvm.helper.TicketModelAssembler;
import com.cyrilselyanin.bustvm.service.TicketService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RepositoryRestController
public class TicketController {
    private final TicketService ticketService;
    private final TicketModelAssembler ticketModelAssembler;
    private final BusTripModelAssembler busTripModelAssembler;

    public TicketController(
            TicketService ticketService,
            TicketModelAssembler ticketModelAssembler,
            BusTripModelAssembler busTripModelAssembler
    ) {
        this.ticketService = ticketService;
        this.ticketModelAssembler = ticketModelAssembler;
        this.busTripModelAssembler = busTripModelAssembler;
    }

    @GetMapping("/api/tickets/{id}")
    public ResponseEntity<EntityModel<Ticket>> getTicketById(@PathVariable Long id) {
        return ticketService.getTicket(id)
                    .map(ticket -> {
                        EntityModel<Ticket> ticketRepresentation = ticketModelAssembler
                                .toModel(ticket)
                                .add(
                                        linkTo(
                                                methodOn(TicketController.class)
                                                        .getAllTickets()
                                        ).withRel("tickets")
                                );
                        return ResponseEntity.ok(ticketRepresentation);
                    })
                    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/api/tickets")
    public ResponseEntity<CollectionModel<EntityModel<Ticket>>> getAllTickets() {
        return ResponseEntity.ok(
                ticketModelAssembler.toCollectionModel(
                        ticketService.getAllTickets()
                )
        );
    }

    @GetMapping("/api/tickets/{id}/busTrip")
    public ResponseEntity<EntityModel<BusTrip>> getTicketBusTrip(
            @PathVariable(name = "id") Long ticketId
    ) {
        return ticketService.getTicketBusTrip(ticketId)
                .map(busTrip -> {
                    EntityModel<BusTrip> busTripRepresentation = busTripModelAssembler
                            .toModel(busTrip);
                    return ResponseEntity.ok(busTripRepresentation);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
