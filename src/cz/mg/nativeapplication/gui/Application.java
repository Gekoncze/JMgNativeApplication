package cz.mg.nativeapplication.gui;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.nativeapplication.gui.components.MainWindow;
import cz.mg.nativeapplication.gui.icons.IconGallery;
import cz.mg.nativeapplication.gui.services.ApplicationProvider;
import cz.mg.nativeapplication.gui.services.IconGalleryCreator;


public @Utility class Application {
    private final @Mandatory @Shared ApplicationProvider applicationProvider = new ApplicationProvider();
    private final @Mandatory @Shared IconGalleryCreator iconGalleryCreator = new IconGalleryCreator();

    private final @Mandatory ApplicationState applicationState;
    private final @Mandatory IconGallery iconGallery;
    private final @Mandatory MainWindow mainWindow;

    public Application() {
        applicationProvider.set(this);
        applicationState = new ApplicationState();
        iconGallery = iconGalleryCreator.create();
        mainWindow = new MainWindow();
    }

    public ApplicationState getApplicationState() {
        return applicationState;
    }

    public IconGallery getIconGallery() {
        return iconGallery;
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }
}
