package com.cyrilselyanin.bustvm.controller;

import com.cyrilselyanin.bustvm.domain.Carrier;
import com.cyrilselyanin.bustvm.service.carrier.CarrierService;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/carriers")
@Validated
public class CarrierController {
    private final CarrierService carrierService;

    public CarrierController(CarrierService carrierService) {
        this.carrierService = carrierService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestBody
            @NotEmpty
            @NotNull
                    Carrier carrier) {
        carrierService.create(carrier);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
