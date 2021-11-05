package cz.mg.nativeapplication.mg.entities.annotations.services.utilities;

import cz.mg.annotations.classes.Utility;


public @Utility class CharacterValidator {
    private final Boolean[] map = new Boolean[255];

    public CharacterValidator(Character... validCharacters) {
        for (char validCharacter : validCharacters) {
            map[validCharacter] = true;
        }
    }

    public boolean isValid(char ch) {
        if (ch < 255) {
            return map[ch] != null;
        } else {
            return false;
        }
    }
}
