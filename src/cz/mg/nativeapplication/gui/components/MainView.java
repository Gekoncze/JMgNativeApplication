package cz.mg.nativeapplication.gui.components;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.nativeapplication.gui.MainWindow;
import cz.mg.nativeapplication.gui.components.controls.UiPanel;

import java.awt.*;

import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Alignment.MIDDLE;
import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Fill.BOTH;


public @Utility class MainView extends UiPanel {
    private static final int BORDER = 2;
    private static final int PADDING = 2;

    private final @Mandatory @Link ProjectTreeView projectTreeView;
    private final @Mandatory @Link MainTabView mainTabView;

    public MainView(@Mandatory MainWindow mainWindow) {
        super(BORDER, PADDING, MIDDLE);

        projectTreeView = new ProjectTreeView(mainWindow);
        projectTreeView.setPreferredSize(new Dimension(0, 0)); // todo - can be handled in ui panel
        projectTreeView.setMinimumSize(new Dimension(0, 0)); // todo
        add(projectTreeView, 0, 0, 1, 1, MIDDLE, BOTH);

        mainTabView = new MainTabView(mainWindow);
        mainTabView.setPreferredSize(new Dimension(0, 0)); // todo
        mainTabView.setMinimumSize(new Dimension(0, 0)); // todo
        add(mainTabView, 1, 0, 3, 1, MIDDLE, BOTH);

        rebuild();
    }

    public @Mandatory ProjectTreeView getProjectTreeView() {
        return projectTreeView;
    }

    public @Mandatory MainTabView getMainTabView() {
        return mainTabView;
    }
}
