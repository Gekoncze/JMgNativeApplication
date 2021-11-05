package cz.mg.nativeapplication.mg.entities.annotations.services.exceptions;

import cz.mg.annotations.requirement.Mandatory;


public class ValidationException extends RuntimeException {
    public ValidationException(@Mandatory String message) {
        super(message);
    }
}
