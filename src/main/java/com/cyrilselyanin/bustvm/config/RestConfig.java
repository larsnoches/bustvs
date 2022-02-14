package com.cyrilselyanin.bustvm.config;

import com.cyrilselyanin.bustvm.domain.Bus;
import com.cyrilselyanin.bustvm.domain.BusPointType;
import com.cyrilselyanin.bustvm.domain.Carrier;
import com.cyrilselyanin.bustvm.domain.SeatState;
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
                BusPointType.class
        );
    }
}