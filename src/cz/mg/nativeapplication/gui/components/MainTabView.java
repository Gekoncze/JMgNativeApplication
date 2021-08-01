package cz.mg.nativeapplication.gui.components;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.nativeapplication.gui.components.controls.UiButton;
import cz.mg.nativeapplication.gui.components.controls.UiLabel;
import cz.mg.nativeapplication.gui.components.controls.UiPanel;
import cz.mg.nativeapplication.gui.components.entity.EntityView;
import cz.mg.nativeapplication.gui.components.other.ObjectView;
import cz.mg.nativeapplication.gui.components.other.RefreshableView;
import cz.mg.nativeapplication.gui.handlers.FocusGainedUserEventHandler;
import cz.mg.nativeapplication.gui.handlers.MouseClickUserEventHandler;

import javax.swing.*;
import java.awt.*;

import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Alignment.MIDDLE;
import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Fill.BOTH;
import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Fill.NONE;
import static cz.mg.nativeapplication.gui.other.NavigationCache.Node;


public class MainTabView extends JTabbedPane implements RefreshableView {
    private static final int PADDING = 8;

    private final @Mandatory @Link MainWindow mainWindow;

    public MainTabView(@Mandatory MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void open(@Optional Node node){
        if(node != null){
            Integer tabIndex = getTabIndex(node.getSelf());
            if(tabIndex == null){
                openNew(node);
            } else {
                openExisting(tabIndex);
            }
        }
    }

    private void openExisting(@Mandatory Integer tabIndex){
        setSelectedIndex(tabIndex);
    }

    private void openNew(@Mandatory Node node){
        if(node.getSelf().getClass().isAnnotationPresent(Entity.class)){
            addTab(node, new EntityView(mainWindow, node.getSelf()));
        }

        // todo - add support for more object types
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

    public void selectNextTab(){
        if(getTabCount() == 0) return;
        int i = getSelectedIndex() + 1;
        if(i >= getTabCount()) i = 0;
        setSelectedIndex(i);
    }

    public void selectPreviousTab(){
        if(getTabCount() == 0) return;
        int i = getSelectedIndex() - 1;
        if(i < 0) i = getTabCount() - 1;
        setSelectedIndex(i);
    }

    private void selectTab(@Mandatory UiPanel header){
        for(int i = 0; i < getTabCount(); i++){
            Component component = getTabComponentAt(i);
            if(component == header){
                setSelectedIndex(i);
            }
        }
    }

    private void addTab(@Mandatory Node node, @Mandatory ObjectView view){
        addTab(null, null, view);
        setTabComponentAt(getTabCount() - 1, createTabHeader(node, view));
        setSelectedIndex(getTabCount() - 1);
    }

    private UiPanel createTabHeader(@Mandatory Node node, @Mandatory Component component) {
        UiPanel header = new UiPanel(0, PADDING, MIDDLE);

        UiLabel label = new UiLabel(node.getIcon(), node.getName());
        label.addMouseListener(new MouseClickUserEventHandler(event -> selectTab(header)));
        label.addFocusListener(new FocusGainedUserEventHandler(() -> selectTab(header)));
        header.add(label, 0, 0, 0, 0, MIDDLE, BOTH);

        UiButton closeButton = new UiButton(mainWindow, null, "x", "Close", () -> remove(component));
        closeButton.setForeground(new Color(180, 180, 180, 255));
        header.add(closeButton, 1, 0, 0, 0, MIDDLE, NONE);

        header.rebuild();
        return header;
    }

    private @Optional Integer getTabIndex(@Mandatory Object object){
        for(int i = 0; i < getTabCount(); i++){
            Component component = getComponentAt(i);
            if(component instanceof ObjectView){
                ObjectView objectView = (ObjectView) component;
                if(objectView.getObject() == object){
                    return i;
                }
            }
        }
        return null;
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
