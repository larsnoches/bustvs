package com.cyrilselyanin.bustvm.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "carriers")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Carrier {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "carriers_gen")
    @SequenceGenerator(
            name = "carriers_gen",
            sequenceName="carriers_seq",
            allocationSize = 1)
    @Column(name = "carrier_id")
    private Long id;

    @NotNull(message = "Name isn't set")
    @NotBlank(message = "Name cannot be empty")
    @Size(
            min = 2,
            max = 255,
            message = "Name must be between 2 and 255 characters")
    @Column(name = "carrier_name", length = 255)
    private String name;

    @NotNull(message = "INN isn't set")
    @NotBlank(message = "INN cannot be empty")
    @Digits(
            integer = 12,
            fraction = 0,
            message = "Wrong INN is set"
    )
    private Integer inn;

    @NotNull(message = "Address isn't set")
    @NotBlank(message = "Address cannot be empty")
    @Size(
            min = 7,
            max = 255,
            message = "Address must be between 7 and 255 characters")
    @Column(name = "carrier_name", length = 255)
    private String address;
}
