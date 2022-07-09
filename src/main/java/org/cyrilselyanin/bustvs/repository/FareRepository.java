package org.cyrilselyanin.bustvs.repository;

import org.cyrilselyanin.bustvs.domain.Fare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FareRepository extends JpaRepository<Fare, Long> {
}
