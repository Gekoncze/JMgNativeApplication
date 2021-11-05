package cz.mg.nativeapplication.mg.entities.annotations.services.exceptions;

import cz.mg.annotations.requirement.Mandatory;


public class TypeValidationException extends ValidationException {
    public TypeValidationException(@Mandatory Class expectation, @Mandatory Class reality) {
        super("Expected object of type " + expectation.getSimpleName() + ", but got " + reality.getSimpleName() + ".");
    }
}
