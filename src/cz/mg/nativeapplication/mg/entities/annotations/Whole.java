package cz.mg.nativeapplication.mg.entities.annotations;

import cz.mg.annotations.requirement.Optional;
import cz.mg.entity.validator.ValidationException;
import cz.mg.entity.validator.Validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Whole {
    Validator validator = new Validator<Integer>() {
        @Override
        public void validate(@Optional Integer number) {
            if(number != null){
                if(number < 0){
                    throw new ValidationException("Expected whole number, but got " + number + ".");
                }
            }
        }
    };
}
