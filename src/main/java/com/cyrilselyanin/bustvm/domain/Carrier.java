package com.cyrilselyanin.bustvm.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;

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
    @Column(name = "carrier_id", nullable = false)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Size(
            min = 2,
            max = 255,
            message = "Name must be between 2 and 255 characters")
    @Column(name = "carrier_name", length = 255, nullable = false)
    private String name;

    @NotNull(message = "INN isn't set")
//    @Digits(
//            integer = 12,
//            fraction = 0,
//            message = "Wrong INN is set"
//    )
    @Min(value = 100000, message = "INN must be more")
//    @Max(value = 999999999, message = "INN must be less than 14")
    @Column(name = "carrier_inn", nullable = false)
    private Integer inn;

    @NotBlank(message = "Address cannot be empty")
    @Size(
            min = 7,
            max = 255,
            message = "Address must be between 7 and 255 characters")
    @Column(name = "carrier_address", length = 255, nullable = false)
    private String address;
}
