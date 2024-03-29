package org.cyrilselyanin.bustvs.config;

import org.cyrilselyanin.bustvs.domain.*;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Component
public class RestConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(
            RepositoryRestConfiguration config,
            CorsRegistry cors
    ) {
        config.exposeIdsFor(
                SeatState.class,
                Carrier.class,
                Bus.class,
                BusPointType.class,
                Fare.class,
                BusPoint.class,
                BusTrip.class,
                Seat.class,
                Ticket.class
        );
    }
}