package com.cyrilselyanin.bustvm.validation;

import org.springframework.validation.Errors;

public class TvmValidationErrorBuilder {
    public static TvmValidationError fromBindingErrors(Errors errors) {
        TvmValidationError error = new TvmValidationError(
                String.format(
                        "Validation failed, %d error(s)",
                        errors.getErrorCount()
                        )
        );
        errors.getAllErrors()
                .forEach(
                        err -> error.addValidationError(
                                err.getDefaultMessage()
                        )
                );
        return error;
    }
}
