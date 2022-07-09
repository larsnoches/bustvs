package org.cyrilselyanin.bustvs.repository;

import org.cyrilselyanin.bustvs.domain.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Long> {
}