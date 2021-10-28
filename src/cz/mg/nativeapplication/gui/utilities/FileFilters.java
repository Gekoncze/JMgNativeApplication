package cz.mg.nativeapplication.gui.utilities;

import cz.mg.annotations.classes.Utility;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public @Utility class FileFilters {
    public static final FileFilter MG = new FileNameExtensionFilter("Mg Project Files (*.mg)", "mg");
}
