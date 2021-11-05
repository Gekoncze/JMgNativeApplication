package cz.mg.nativeapplication.mg.entities.annotations.services.validators;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.mg.entities.annotations.services.exceptions.TypeValidationException;
import cz.mg.nativeapplication.mg.entities.annotations.services.exceptions.ValidationException;
import cz.mg.nativeapplication.mg.entities.annotations.services.utilities.CharacterValidator;


public @Service class NameValidator implements Validator {
    private static final NameValidator instance = new NameValidator();
    private static final CharacterValidator characterValidator = new CharacterValidator(
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' '
    );

    public static NameValidator getInstance(){
        return instance;
    }

    private NameValidator() {
    }

    @Override
    public void validate(@Optional Object object) {
        if(object != null){
            if(object instanceof String){
                String name = (String) object;
                for(int i = 0; i < name.length(); i++){
                    char ch = name.charAt(i);
                    if(!characterValidator.isValid(ch)){
                        throw new ValidationException("Invalid name character '" + ch + "' at index " + i + ".");
                    }
                }
            } else {
                throw new TypeValidationException(String.class, object.getClass());
            }
        }
    }
}
