package cz.mg.nativeapplication.mg.services.history;

import cz.mg.annotations.classes.Utility;


public @Utility interface Action {
    void redo();
    void undo();
}
