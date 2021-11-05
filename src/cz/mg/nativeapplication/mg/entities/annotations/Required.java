package cz.mg.nativeapplication.mg.entities.annotations;

import cz.mg.nativeapplication.mg.entities.annotations.services.validators.RequiredValidator;
import cz.mg.nativeapplication.mg.entities.annotations.services.validators.Validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Required {
    Validator validator = RequiredValidator.getInstance();
}
