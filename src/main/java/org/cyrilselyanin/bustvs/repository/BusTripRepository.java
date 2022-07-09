package org.cyrilselyanin.bustvs.repository;

import org.cyrilselyanin.bustvs.domain.BusTrip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusTripRepository extends JpaRepository<BusTrip, Long> {
}
