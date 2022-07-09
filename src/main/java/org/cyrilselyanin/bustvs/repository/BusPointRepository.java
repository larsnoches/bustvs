package org.cyrilselyanin.bustvs.repository;

import org.cyrilselyanin.bustvs.domain.BusPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusPointRepository extends JpaRepository<BusPoint, Long> {
}
