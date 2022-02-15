package com.cyrilselyanin.bustvm.repository;

import com.cyrilselyanin.bustvm.domain.BusPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusPointRepository extends JpaRepository<BusPoint, Long> {
}
