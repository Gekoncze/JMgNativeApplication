package cz.mg.nativeapplication.gui;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.explorer.Explorer;
import cz.mg.nativeapplication.gui.components.ExplorerWindow;
import cz.mg.nativeapplication.gui.images.ImageGallery;
import cz.mg.nativeapplication.gui.services.ApplicationProvider;
import cz.mg.nativeapplication.gui.services.EntityMapperProvider;
import cz.mg.nativeapplication.mg.entities.MgProject;


public @Utility class Application {
    private final @Mandatory @Shared ApplicationProvider applicationProvider = new ApplicationProvider();
    private final @Mandatory @Shared EntityMapperProvider entityMapperProvider = new EntityMapperProvider();

    private final @Mandatory Explorer explorer;
    private final @Mandatory ImageGallery imageGallery;
    private final @Mandatory ExplorerWindow explorerWindow;

    public Application() {
        applicationProvider.set(this);
        explorer = new Explorer(entityMapperProvider.get());
        imageGallery = new ImageGallery();
        explorerWindow = new ExplorerWindow(explorer);
    }

    public @Mandatory Explorer getExplorer() {
        return explorer;
    }

    public @Mandatory ImageGallery getImageGallery() {
        return imageGallery;
    }

    public @Mandatory ExplorerWindow getMainWindow() {
        return explorerWindow;
    }

    public @Mandatory MgProject getProject() {
        if(explorer.getRoot() != null){
            return (MgProject) explorer.getRoot();
        } else {
            throw new RuntimeException("Missing project.");
        }
    }
}
