package com.cyrilselyanin.bustvm.repository;

import com.cyrilselyanin.bustvm.domain.Fare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FareRepository extends JpaRepository<Fare, Long> {
}
