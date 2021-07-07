package cz.mg.nativeapplication.gui.components;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.nativeapplication.gui.MainWindow;

import javax.swing.*;
import java.awt.*;


public @Utility class MainView extends JPanel {
    public MainView(@Mandatory MainWindow mainWindow) {
        setLayout(new GridBagLayout());

        GridBagConstraints projectTreeViewSettings = new GridBagConstraints();
        projectTreeViewSettings.gridx = 0;
        projectTreeViewSettings.gridy = 0;
        projectTreeViewSettings.weightx = 1;
        projectTreeViewSettings.weighty = 1;
        projectTreeViewSettings.fill = GridBagConstraints.BOTH;
        projectTreeViewSettings.insets = new Insets(4, 4, 4, 0);
        ProjectTreeView projectTreeView = new ProjectTreeView(mainWindow);
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
        ComponentTabsView componentTabsView = new ComponentTabsView();
        componentTabsView.setPreferredSize(new Dimension(0, 0));
        componentTabsView.setMinimumSize(new Dimension(0, 0));
        add(componentTabsView, componentTabsViewSettings);
    }
}
