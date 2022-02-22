package com.cyrilselyanin.bustvm.repository;

import com.cyrilselyanin.bustvm.domain.Ticket;
import com.cyrilselyanin.bustvm.validation.authorize.HasAuthorization;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

//@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @RestResource(exported = false)
    Optional<Ticket> findByIdAndUserId(Long id, String userId);

    @RestResource(exported = false)
    List<Ticket> findAllByUserId(String userId);

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @Override
    Optional<Ticket> findById(Long id);

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @Override
    List<Ticket> findAll();

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @Override
    List<Ticket> findAll(Sort sort);

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @Override
    Page<Ticket> findAll(Pageable pageable);
}
