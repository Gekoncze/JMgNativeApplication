package cz.mg.nativeapplication.gui.components;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.nativeapplication.entities.mg.MgProject;
import cz.mg.nativeapplication.gui.MainWindow;
import cz.mg.nativeapplication.gui.components.object.ProjectView;

import javax.swing.*;

import static cz.mg.nativeapplication.gui.utilities.NavigationCache.Node;


public class MainTabView extends JTabbedPane {
    private final @Mandatory @Link MainWindow mainWindow;

    public MainTabView(@Mandatory MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void open(@Optional Node node){
        if(node != null){
            if(node.getSelf() instanceof MgProject){
                addTab(
                    node.getName(),
                    node.getIcon(),
                    new ProjectView(mainWindow, (MgProject) node.getSelf())
                );
            }

            // todo - add support for more object types
        }
    }
}
