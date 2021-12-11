package cz.mg.nativeapplication.gui.components.views;

import cz.mg.annotations.classes.Utility;
import cz.mg.nativeapplication.gui.components.other.Refreshable;

import java.awt.event.KeyEvent;


public @Utility interface ObjectView extends Refreshable {
    Object getObject();
    void onKeyEvent(KeyEvent e);
}
