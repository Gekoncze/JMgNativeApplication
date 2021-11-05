package cz.mg.nativeapplication.mg.entities.annotations.services.validators;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.mg.entities.annotations.services.exceptions.TypeValidationException;
import cz.mg.nativeapplication.mg.entities.annotations.services.exceptions.ValidationException;


public @Service class WholeValidator implements Validator {
    private static final WholeValidator instance = new WholeValidator();

    public static WholeValidator getInstance() {
        return instance;
    }

    private WholeValidator() {
    }

    @Override
    public void validate(@Optional Object object) {
        if(object != null){
            if(object instanceof Integer){
                Integer number = (Integer) object;
                if(number < 0){
                    throw new ValidationException("Expected whole number, but got " + number + ".");
                }
            } else {
                throw new TypeValidationException(Integer.class, object.getClass());
            }
        }
    }
}
