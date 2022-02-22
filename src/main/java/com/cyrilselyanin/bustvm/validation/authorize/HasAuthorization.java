package com.cyrilselyanin.bustvm.validation.authorize;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('ROLE_USER') and authentication.principal.getName().equals(#userId)" +
        "or hasRole('USER_ADMIN')")
public @interface HasAuthorization {
}
