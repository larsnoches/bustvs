package com.cyrilselyanin.bustvm.service.carrier;

import com.cyrilselyanin.bustvm.domain.Carrier;

import java.util.List;

public interface CarrierService {
    void create(Carrier carrier);
    Carrier getOne(Long id);
    List<Carrier> getAll();
    boolean update(Carrier carrier);
    boolean delete(Long id);
}
