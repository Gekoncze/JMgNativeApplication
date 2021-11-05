package cz.mg.nativeapplication.mg.entities.annotations.services.validators;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.mg.entities.annotations.services.exceptions.ValidationException;


public @Service class RequiredValidator implements Validator {
    private static final RequiredValidator instance = new RequiredValidator();

    public static RequiredValidator getInstance(){
        return instance;
    }

    private RequiredValidator() {
    }

    @Override
    public void validate(@Optional Object object) {
        if(object == null){
            throw new ValidationException("Missing required value.");
        }
    }
}
