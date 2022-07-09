package org.cyrilselyanin.bustvs.repository;

import org.cyrilselyanin.bustvs.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
