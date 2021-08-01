package cz.mg.nativeapplication.gui.components;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.nativeapplication.gui.components.controls.UiHorizontalSplitPane;

import javax.swing.border.EmptyBorder;
import java.awt.*;


public @Utility class MainView extends UiHorizontalSplitPane {
    private static final int DEFAULT_DIVIDER_POSITION = 360;
    private static final int BORDER = 4;

    public MainView(@Mandatory MainWindow mainWindow) {
        super(
            new MainProjectTreeView(mainWindow),
            new MainTabView(mainWindow)
        );

        setDividerLocation(DEFAULT_DIVIDER_POSITION);
        setBorder(new EmptyBorder(new Insets(BORDER, BORDER, BORDER, BORDER)));
    }

    public @Mandatory MainProjectTreeView getProjectTreeView() {
        return (MainProjectTreeView) getLeftComponent();
    }

    public @Mandatory MainTabView getMainTabView() {
        return (MainTabView) getRightComponent();
    }
}
