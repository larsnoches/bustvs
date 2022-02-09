package com.cyrilselyanin.bustvm.repository;

import com.cyrilselyanin.bustvm.domain.SeatState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatStateRepository extends JpaRepository<SeatState, Long> {
}
