package cz.mg.nativeapplication.explorer.history;

import cz.mg.annotations.classes.Utility;


public @Utility interface Action {
    void redo();
    void undo();
}
