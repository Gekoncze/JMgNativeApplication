package cz.mg.nativeapplication.gui;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.nativeapplication.explorer.Explorer;
import cz.mg.nativeapplication.gui.components.MainWindow;
import cz.mg.nativeapplication.gui.images.ImageGallery;
import cz.mg.nativeapplication.gui.services.ApplicationProvider;
import cz.mg.nativeapplication.gui.utilities.ApplicationState;


public @Utility class Application {
    private final @Mandatory @Shared ApplicationProvider applicationProvider = new ApplicationProvider();

    private final @Mandatory Explorer explorer;
    private final @Mandatory ApplicationState applicationState;
    private final @Mandatory ImageGallery imageGallery;
    private final @Mandatory MainWindow mainWindow;

    public Application() {
        applicationProvider.set(this);
        explorer = new Explorer();
        applicationState = new ApplicationState();
        imageGallery = new ImageGallery();
        mainWindow = new MainWindow();
    }

    public @Mandatory Explorer getExplorer() {
        return explorer;
    }

    public @Mandatory ApplicationState getApplicationState() {
        return applicationState;
    }

    public @Mandatory ImageGallery getImageGallery() {
        return imageGallery;
    }

    public @Mandatory MainWindow getMainWindow() {
        return mainWindow;
    }
}
