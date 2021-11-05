package cz.mg.nativeapplication.mg.entities.annotations.services.validators;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Optional;


public @Service interface Validator {
    void validate(@Optional Object object);
}
