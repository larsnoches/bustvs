package com.cyrilselyanin.bustvm.service.carrier;

import com.cyrilselyanin.bustvm.domain.Carrier;
import com.cyrilselyanin.bustvm.repository.CarrierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrierServiceImpl implements CarrierService {
    private final CarrierRepository carrierRepository;

    public CarrierServiceImpl(CarrierRepository carrierRepository) {
        this.carrierRepository = carrierRepository;
    }

    @Override
    public void create(Carrier carrier) {
        carrierRepository.save(carrier);
    }

    @Override
    public Carrier getOne(Long id) {
//        if (id < 0) return null;
        return carrierRepository.getById(id);
    }

    @Override
    public List<Carrier> getAll() {
        return carrierRepository.findAll();
    }

    @Override
    public boolean update(Carrier carrier) {
        if (carrier.getId() < 0) return false;
        if (!carrierRepository.existsById(carrier.getId())) {
            return false;
        }
        carrierRepository.save(carrier);
        return true;
    }

    @Override
    public boolean delete(Long id) {
//        if (id < 0) return false;
        carrierRepository.deleteById(id);
        return true;
    }
}
