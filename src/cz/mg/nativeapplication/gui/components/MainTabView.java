package cz.mg.nativeapplication.gui.components;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.nativeapplication.gui.MainWindow;
import cz.mg.nativeapplication.gui.components.controls.UiButton;
import cz.mg.nativeapplication.gui.components.controls.UiLabel;
import cz.mg.nativeapplication.gui.components.controls.UiPanel;
import cz.mg.nativeapplication.gui.components.entity.EntityView;

import javax.swing.*;
import java.awt.*;

import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Alignment.MIDDLE;
import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Fill.BOTH;
import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Fill.NONE;
import static cz.mg.nativeapplication.gui.utilities.NavigationCache.Node;


public class MainTabView extends JTabbedPane implements RefreshableView {
    private static final int PADDING = 8;

    private final @Mandatory @Link MainWindow mainWindow;

    public MainTabView(@Mandatory MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void open(@Optional Node node){
        if(node != null){
            Object object = node.getSelf();
            if(!hasObjectOpened(object)){
                if(object.getClass().isAnnotationPresent(Entity.class)){
                    addTab(node, new EntityView(mainWindow, object));
                }

                // todo - add support for more object types
            }
        }
    }

    public void closeActiveTab(){
        int index = getSelectedIndex();
        if(index != -1){
            remove(index);
        }
    }

    public void closeAllTabs(){
        while(getTabCount() > 0){
            remove(0);
        }
    }

    private void addTab(@Mandatory Node node, @Mandatory ObjectView view){
        addTab(null, null, view);
        setTabComponentAt(getTabCount() - 1, createTabHeader(node, view));
        setSelectedIndex(getTabCount() - 1);
    }

    private UiPanel createTabHeader(@Mandatory Node node, @Mandatory Component component) {
        UiPanel header = new UiPanel(0, PADDING, MIDDLE);

        header.add(new UiLabel(node.getIcon(), node.getName()), 0, 0, 0, 0, MIDDLE, BOTH);

        UiButton closeButton = new UiButton(mainWindow, null, "x", "Close", () -> remove(component));
        closeButton.setForeground(new Color(180, 180, 180, 255));
        header.add(closeButton, 1, 0, 0, 0, MIDDLE, NONE);

        header.rebuild();
        return header;
    }

    private boolean hasObjectOpened(@Mandatory Object object){
        for(int i = 0; i < getTabCount(); i++){
            Component component = getComponentAt(i);
            if(component instanceof ObjectView){
                ObjectView objectView = (ObjectView) component;
                if(objectView.getObject() == object){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void refresh() {
        for(int i = 0; i < getTabCount(); i++){
            Component component = getComponentAt(i);
            if(component instanceof ObjectView){
                ObjectView objectView = (ObjectView) component;
                objectView.refresh();
            }
        }
    }
}
