package org.cyrilselyanin.bustvs.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "seats_states")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SeatState {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seats_states_gen")
    @SequenceGenerator(
            name = "seats_states_gen",
            sequenceName = "seats_states_seq",
            allocationSize = 1)
    @Column(name = "seat_state_id", nullable = false)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Size(
            min = 2,
            max = 255,
            message = "Name must be between 2 and 255 characters")
    @Column(name = "seat_state_name", length = 255, nullable = false)
    private String name;

    @OneToMany(mappedBy = "seatState")
    private Set<Seat> seats;
}
