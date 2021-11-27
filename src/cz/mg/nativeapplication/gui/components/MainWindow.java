package cz.mg.nativeapplication.gui.components;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Cache;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Shared;
import cz.mg.nativeapplication.gui.components.other.ObjectView;
import cz.mg.nativeapplication.gui.components.other.Refreshable;
import cz.mg.nativeapplication.gui.event.WindowCloseUserEventHandler;
import cz.mg.nativeapplication.gui.event.KeyDispatcherUserEventHandler;
import cz.mg.nativeapplication.gui.icons.ImageGallery;
import cz.mg.nativeapplication.gui.services.ApplicationProvider;
import cz.mg.nativeapplication.gui.services.MainWindowProvider;
import cz.mg.nativeapplication.gui.utilities.Navigation;
import cz.mg.nativeapplication.gui.services.NavigationCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashSet;


public @Utility class MainWindow extends JFrame implements Refreshable {
    private static final String TITLE = "JMgNativeApplication";
    private static final int DEFAULT_WIDTH = 1600;
    private static final int DEFAULT_HEIGHT = 900;

    private final @Mandatory @Shared ApplicationProvider applicationProvider = new ApplicationProvider();
    private final @Mandatory @Shared MainWindowProvider mainWindowProvider = new MainWindowProvider();

    private final @Mandatory @Link MainMenu mainMenu;
    private final @Mandatory @Link MainView mainView;

    private @Optional @Cache Navigation navigation;

    public MainWindow() {
        mainWindowProvider.set(this);
        mainMenu = new MainMenu();
        mainView = new MainView();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowCloseUserEventHandler(this::onWindowCloseButtonClicked));
        setTitle(TITLE);
        setIconImage(applicationProvider.get().getImageGallery().getImageOptional(ImageGallery.MG));
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setJMenuBar(mainMenu);
        setContentPane(mainView);
        setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, new HashSet<>());
        setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, new HashSet<>());
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
            new KeyDispatcherUserEventHandler(this::onKeyEvent)
        );
        refresh();
    }

    public @Mandatory Navigation getNavigation() {
        if(navigation == null){
            navigation = new NavigationCreator().create(
                applicationProvider.get().getApplicationState().getProject()
            );
        }

        return navigation;
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

    private void onWindowCloseButtonClicked(){
        mainMenu.getActions().exit();
    }
}
