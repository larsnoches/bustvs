package com.cyrilselyanin.bustvm.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.cyrilselyanin.bustvm.domain.BusTrip;
import com.cyrilselyanin.bustvm.domain.Ticket;
import com.cyrilselyanin.bustvm.helper.BusTripModelAssembler;
import com.cyrilselyanin.bustvm.helper.TicketModelAssembler;
import com.cyrilselyanin.bustvm.service.TicketService;
import com.cyrilselyanin.bustvm.validation.notfound.TicketNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
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

    @PostMapping("/tickets")
    public ResponseEntity<?> createTicket(@RequestBody Ticket ticket) {
        EntityModel<Ticket> ticketRepresentation = ticketModelAssembler
                .toModel(ticketService.create(ticket))
                .add(
                        linkTo(
                                methodOn(TicketController.class)
                                        .getAllTickets()
                        ).withRel("tickets")
                );

        return ResponseEntity
                .created(ticketRepresentation.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(ticketRepresentation);
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<EntityModel<Ticket>> getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id)
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
//                .orElseThrow(() -> new TicketNotFoundException(id));
    }

    @GetMapping("/tickets")
    public ResponseEntity<CollectionModel<EntityModel<Ticket>>> getAllTickets() {
        return ResponseEntity.ok(
                ticketModelAssembler.toCollectionModel(
                        ticketService.getAllTickets()
                )
        );
    }

    @PutMapping("/tickets/{id}")
    public ResponseEntity<?> replaceTicket(@RequestBody Ticket ticket, @PathVariable Long id) {
        Ticket updatedTicket = ticketService.update(ticket, id);
        EntityModel<Ticket> ticketRepresentation = ticketModelAssembler.toModel(updatedTicket);

        return ResponseEntity
                .created(ticketRepresentation.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(ticketRepresentation);
    }

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable Long id) {
        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tickets/{id}/busTrip")
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
