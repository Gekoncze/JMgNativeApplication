package cz.mg.nativeapplication.gui.components;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.nativeapplication.gui.MainWindow;

import javax.swing.*;
import java.awt.*;


public @Utility class MainView extends JPanel {
    private final @Mandatory @Link ProjectTreeView projectTreeView;
    private final @Mandatory @Link MainTabView mainTabView;

    public MainView(@Mandatory MainWindow mainWindow) {
        setLayout(new GridBagLayout());

        GridBagConstraints projectTreeViewSettings = new GridBagConstraints();
        projectTreeViewSettings.gridx = 0;
        projectTreeViewSettings.gridy = 0;
        projectTreeViewSettings.weightx = 1;
        projectTreeViewSettings.weighty = 1;
        projectTreeViewSettings.fill = GridBagConstraints.BOTH;
        projectTreeViewSettings.insets = new Insets(4, 4, 4, 0);

        projectTreeView = new ProjectTreeView(mainWindow);
        projectTreeView.setPreferredSize(new Dimension(0, 0));
        projectTreeView.setMinimumSize(new Dimension(0, 0));
        add(projectTreeView, projectTreeViewSettings);

        GridBagConstraints componentTabsViewSettings = new GridBagConstraints();
        componentTabsViewSettings.gridx = 1;
        componentTabsViewSettings.gridy = 0;
        componentTabsViewSettings.weightx = 3;
        componentTabsViewSettings.weighty = 1;
        componentTabsViewSettings.fill = GridBagConstraints.BOTH;
        componentTabsViewSettings.insets = new Insets(4, 4, 4, 4);

        mainTabView = new MainTabView(mainWindow);
        mainTabView.setPreferredSize(new Dimension(0, 0));
        mainTabView.setMinimumSize(new Dimension(0, 0));
        add(mainTabView, componentTabsViewSettings);
    }

    public ProjectTreeView getProjectTreeView() {
        return projectTreeView;
    }

    public MainTabView getMainTabView() {
        return mainTabView;
    }
}
