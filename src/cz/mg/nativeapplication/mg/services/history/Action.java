package cz.mg.nativeapplication.mg.services.history;

import cz.mg.annotations.classes.Utility;


public @Utility interface Action {
    public void redo();
    public void undo();
}
