package com.cyrilselyanin.bustvm.domain;

import lombok.*;

import javax.persistence.*;

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
            allocationSize=1)
    @Column(name = "carrier_id")
    private Long id;

    private String name;
    private Integer inn;
    private String address;
}
