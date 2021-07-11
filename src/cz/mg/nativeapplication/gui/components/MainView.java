package cz.mg.nativeapplication.gui.components;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.nativeapplication.gui.MainWindow;
import cz.mg.nativeapplication.gui.utilities.GridSettingsFactory;

import javax.swing.*;
import java.awt.*;


public @Utility class MainView extends JPanel {
    private static final int PADDING = 2;

    private final @Mandatory @Link ProjectTreeView projectTreeView;
    private final @Mandatory @Link MainTabView mainTabView;

    public MainView(@Mandatory MainWindow mainWindow) {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));

        projectTreeView = new ProjectTreeView(mainWindow);
        projectTreeView.setPreferredSize(new Dimension(0, 0));
        projectTreeView.setMinimumSize(new Dimension(0, 0));
        add(projectTreeView, new GridSettingsFactory().create(0, 0, 1, 1, PADDING));

        mainTabView = new MainTabView(mainWindow);
        mainTabView.setPreferredSize(new Dimension(0, 0));
        mainTabView.setMinimumSize(new Dimension(0, 0));
        add(mainTabView, new GridSettingsFactory().create(1, 0, 3, 1, PADDING));
    }

    public @Mandatory ProjectTreeView getProjectTreeView() {
        return projectTreeView;
    }

    public @Mandatory MainTabView getMainTabView() {
        return mainTabView;
    }
}
