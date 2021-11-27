package cz.mg.nativeapplication.gui;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.nativeapplication.gui.components.MainWindow;
import cz.mg.nativeapplication.gui.icons.ImageGallery;
import cz.mg.nativeapplication.gui.services.ApplicationProvider;
import cz.mg.nativeapplication.gui.services.ImageGalleryCreator;


public @Utility class Application {
    private final @Mandatory @Shared ApplicationProvider applicationProvider = new ApplicationProvider();
    private final @Mandatory @Shared ImageGalleryCreator imageGalleryCreator = new ImageGalleryCreator();

    private final @Mandatory ApplicationState applicationState;
    private final @Mandatory ImageGallery imageGallery;
    private final @Mandatory MainWindow mainWindow;

    public Application() {
        applicationProvider.set(this);
        applicationState = new ApplicationState();
        imageGallery = imageGalleryCreator.create();
        mainWindow = new MainWindow();
    }

    public ApplicationState getApplicationState() {
        return applicationState;
    }

    public ImageGallery getImageGallery() {
        return imageGallery;
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }
}
