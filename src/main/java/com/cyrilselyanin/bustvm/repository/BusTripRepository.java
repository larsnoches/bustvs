package com.cyrilselyanin.bustvm.repository;

import com.cyrilselyanin.bustvm.domain.BusTrip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusTripRepository extends JpaRepository<BusTrip, Long> {
}
