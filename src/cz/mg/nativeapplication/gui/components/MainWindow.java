package cz.mg.nativeapplication.gui.components;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Cache;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.gui.components.other.ObjectView;
import cz.mg.nativeapplication.gui.components.other.RefreshableView;
import cz.mg.nativeapplication.gui.handlers.CloseUserEventHandler;
import cz.mg.nativeapplication.gui.handlers.KeyDispatcherUserEventHandler;
import cz.mg.nativeapplication.gui.icons.IconGallery;
import cz.mg.nativeapplication.gui.other.ApplicationState;
import cz.mg.nativeapplication.gui.other.Navigation;
import cz.mg.nativeapplication.gui.services.IconGalleryProvider;
import cz.mg.nativeapplication.gui.services.NavigationCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashSet;


public @Utility class MainWindow extends JFrame implements RefreshableView {
    private static final String TITLE = "JMgNativeApplication";
    private static final int DEFAULT_WIDTH = 1600;
    private static final int DEFAULT_HEIGHT = 900;

    private final @Mandatory @Part ApplicationState applicationState = new ApplicationState();
    private final @Mandatory @Part MainActions mainActions = new MainActions(this);
    private final @Mandatory @Link MainMenu mainMenu;
    private final @Mandatory @Link MainView mainView;

    private @Optional @Cache Navigation navigation;

    public MainWindow() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new CloseUserEventHandler(mainActions::exit));
        setTitle(TITLE);
        setIconImage(new IconGalleryProvider().get().getImage(IconGallery.MG));
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setJMenuBar(mainMenu = new MainMenu(mainActions));
        setContentPane(mainView = new MainView(this));
        setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, new HashSet<>());
        setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, new HashSet<>());
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
            new KeyDispatcherUserEventHandler(this::onKeyEvent)
        );
        refresh();
    }

    public @Mandatory ApplicationState getApplicationState() {
        return applicationState;
    }

    public @Mandatory Navigation getNavigation() {
        if(navigation == null){
            navigation = new NavigationCreator().create(applicationState.getProject());
        }

        return navigation;
    }

    public @Mandatory MainActions getMainActions() {
        return mainActions;
    }

    public @Mandatory MainMenu getMainMenu() {
        return mainMenu;
    }

    public @Mandatory MainView getMainView() {
        return mainView;
    }

    @Override
    public void refresh() {
        navigation = null;
        getMainView().getProjectTreeView().refresh();
        getMainView().getMainTabView().refresh();
    }

    private boolean onKeyEvent(@Mandatory KeyEvent e){
        ObjectView objectView = getMainView().getMainTabView().getSelectedTab();
        if(objectView != null){
            objectView.onKeyEvent(e);
        }
        return false;
    }
}
