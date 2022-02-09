package com.cyrilselyanin.bustvm.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "seats_states")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class SeatState {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seats_states_gen")
    @SequenceGenerator(
            name = "seats_states_gen",
            sequenceName="seats_states_seq",
            allocationSize=1)
    @Column(name = "seat_state_id")
    private Long id;

    @NotNull(message = "Name isn't set")
    @NotBlank(message = "Name cannot be empty")
    @Size(
            min = 2,
            max = 255,
            message = "Name must be between 2 and 255 characters")
    @Column(name = "seat_state_name", length = 255)
    private String name;
}
