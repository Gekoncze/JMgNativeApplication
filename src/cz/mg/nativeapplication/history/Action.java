package cz.mg.nativeapplication.history;

import cz.mg.annotations.classes.Utility;


public @Utility interface Action {
    public void redo();
    public void undo();
}
