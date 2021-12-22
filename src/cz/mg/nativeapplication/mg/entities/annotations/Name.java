package cz.mg.nativeapplication.mg.entities.annotations;

import cz.mg.annotations.requirement.Optional;
import cz.mg.entity.validator.ValidationException;
import cz.mg.entity.validator.Validator;
import cz.mg.entity.validator.utilities.CharacterValidationUtility;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Name {
    Validator validator = new Validator<String>() {
        private final CharacterValidationUtility characterValidator = new CharacterValidationUtility(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' '
        );

        @Override
        public void validate(@Optional String name) {
            if(name != null){
                for(int i = 0; i < name.length(); i++){
                    char ch = name.charAt(i);
                    if(!characterValidator.isValid(ch)){
                        throw new ValidationException("Invalid name character '" + ch + "' at index " + i + ".");
                    }
                }
            }
        }
    };
}
