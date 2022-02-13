package com.cyrilselyanin.bustvm.repository;

import com.cyrilselyanin.bustvm.domain.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Long> {
}