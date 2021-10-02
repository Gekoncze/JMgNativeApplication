package cz.mg.nativeapplication.gui.components.controls;

import javax.swing.*;
import java.awt.*;


public class UiConstants {
    public static Color getListBackgroundColor(){
        return UIManager.getDefaults().getColor("List.background");
    }

    public static Color getListSelectionBackgroundColor(){
        return UIManager.getDefaults().getColor("List.selectionBackground");
    }
}
