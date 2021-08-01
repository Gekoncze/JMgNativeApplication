package cz.mg.nativeapplication.gui.components.other;

import cz.mg.annotations.classes.Utility;

import javax.swing.*;


public @Utility abstract class ObjectView extends JScrollPane implements RefreshableView {
    public abstract Object getObject();
}
