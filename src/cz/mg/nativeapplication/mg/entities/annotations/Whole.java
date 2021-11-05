package cz.mg.nativeapplication.mg.entities.annotations;

import cz.mg.nativeapplication.mg.entities.annotations.services.validators.Validator;
import cz.mg.nativeapplication.mg.entities.annotations.services.validators.WholeValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Whole {
    Validator validator = WholeValidator.getInstance();
}
