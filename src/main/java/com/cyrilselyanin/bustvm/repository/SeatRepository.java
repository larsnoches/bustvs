package com.cyrilselyanin.bustvm.repository;

import com.cyrilselyanin.bustvm.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
