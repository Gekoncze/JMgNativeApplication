package cz.mg.nativeapplication.gui;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;
import cz.mg.entity.explorer.gui.services.GalleryIconService;
import cz.mg.nativeapplication.gui.icons.Icons;
import cz.mg.nativeapplication.mg.entities.MgProject;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public @Utility class Application {
    private static final String TITLE = "JMgNativeApplication";
    private static final FileFilter MG_FILE_FILTER = new FileNameExtensionFilter("Mg Project Files (*.mg)", "mg");

    private final @Mandatory @Shared Initialization initialization = new Initialization();
    private final @Mandatory @Shared GalleryIconService galleryIconService = new GalleryIconService();

    private final @Mandatory @Shared ExplorerWindow window;

    public Application() {
        initialization.init();
        window = new ExplorerWindow(initialization.createMapper(), MG_FILE_FILTER);
        galleryIconService.initialize(window.getGallery(), Icons.class);
        window.setTitle(TITLE);
        window.setIconImage(window.getGallery().getImage(Icons.MG));
    }

    public @Mandatory ExplorerWindow getWindow() {
        return window;
    }

    public @Mandatory MgProject getProject() {
        if(window.getExplorer().getProject() != null){
            return (MgProject) window.getExplorer().getProject();
        } else {
            throw new RuntimeException("Missing project.");
        }
    }
}
