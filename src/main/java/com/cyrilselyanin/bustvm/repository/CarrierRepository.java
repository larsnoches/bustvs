package com.cyrilselyanin.bustvm.repository;

import com.cyrilselyanin.bustvm.domain.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrierRepository extends JpaRepository<Carrier, Long> {
}