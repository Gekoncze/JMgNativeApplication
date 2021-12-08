package cz.mg.nativeapplication.gui.components;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.nativeapplication.explorer.Explorer;
import cz.mg.nativeapplication.gui.ui.controls.UiHorizontalSplitPane;

import javax.swing.border.EmptyBorder;
import java.awt.*;


public @Utility class MainView extends UiHorizontalSplitPane { // TODO - just create in main window
    private static final int DEFAULT_DIVIDER_POSITION = 360;
    private static final int BORDER = 4;

    public MainView(@Mandatory Explorer explorer) {
        super(
            new NavigationTree(),
            new MainTabView(explorer)
        );

        setDividerLocation(DEFAULT_DIVIDER_POSITION);
        setBorder(new EmptyBorder(new Insets(BORDER, BORDER, BORDER, BORDER)));
    }

    public @Mandatory NavigationTree getProjectTreeView() {
        return (NavigationTree) getLeftComponent();
    }

    public @Mandatory MainTabView getMainTabView() {
        return (MainTabView) getRightComponent();
    }
}
