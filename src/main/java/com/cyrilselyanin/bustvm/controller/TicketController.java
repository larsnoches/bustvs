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

    @GetMapping("/api/tickets")
    public ResponseEntity<CollectionModel<EntityModel<Ticket>>> getAllTickets() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        boolean isUser = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_USER"));
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return ResponseEntity.ok(
                    ticketModelAssembler.toCollectionModel(
                            ticketService.getAllTickets()
                    )
            );
        }
        if (isUser) {
            return ResponseEntity.ok(
                    ticketModelAssembler.toCollectionModel(
                            ticketService.getAllTicketsForUser(userId)
                    )
            );
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/api/tickets/{id}/busTrip")
    public ResponseEntity<EntityModel<BusTrip>> getTicketBusTrip(
            @PathVariable(name = "id") Long ticketId
    ) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        StringBuilder sb = new StringBuilder();
        sb.append("getName: %s\n".formatted(authentication.getName()));
        sb.append("getAuthorities: %s\n".formatted(authentication.getAuthorities().toString()));
        sb.append("getCredentials: %s\n".formatted(authentication.getCredentials().toString()));
        sb.append("getPrincipal: %s\n".formatted(authentication.getPrincipal().toString()));
        sb.append("getDetails: %s\n".formatted(authentication.getDetails()));

        String userId = authentication.getName();
        boolean isUser = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_USER"));
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        return ticketService.getTicketBusTrip(ticketId, userId)
                .map(busTrip -> {
                    EntityModel<BusTrip> busTripRepresentation = busTripModelAssembler
                            .toModel(busTrip);
                    return ResponseEntity.ok(busTripRepresentation);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
