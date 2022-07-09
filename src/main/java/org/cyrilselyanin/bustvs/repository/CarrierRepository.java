package org.cyrilselyanin.bustvs.repository;

import org.cyrilselyanin.bustvs.domain.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrierRepository extends JpaRepository<Carrier, Long> {
}