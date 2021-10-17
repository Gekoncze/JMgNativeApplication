package cz.mg.nativeapplication.gui.components;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.gui.components.controls.UiButton;
import cz.mg.nativeapplication.gui.components.controls.UiLabel;
import cz.mg.nativeapplication.gui.components.controls.UiPanel;
import cz.mg.nativeapplication.gui.components.entity.EntityView;
import cz.mg.nativeapplication.gui.components.other.ObjectView;
import cz.mg.nativeapplication.gui.components.other.Refreshable;
import cz.mg.nativeapplication.gui.handlers.FocusGainedUserEventHandler;
import cz.mg.nativeapplication.gui.handlers.MouseClickUserEventHandler;
import cz.mg.nativeapplication.gui.services.ObjectIconProvider;
import cz.mg.nativeapplication.gui.services.ObjectNameProvider;

import javax.swing.*;
import java.awt.*;

import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Alignment.MIDDLE;
import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Fill.BOTH;
import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Fill.NONE;


public class MainTabView extends JTabbedPane implements Refreshable {
    private static final int PADDING = 8;

    public MainTabView() {
    }

    public void open(@Optional Object object){
        if(object != null){
            Integer tabIndex = getTabIndex(object);
            if(tabIndex == null){
                openNew(object);
            } else {
                openExisting(tabIndex);
            }
        }
    }

    private void openExisting(@Mandatory Integer tabIndex){
        setSelectedIndex(tabIndex);
    }

    private void openNew(@Mandatory Object object){
        if(object.getClass().isAnnotationPresent(Entity.class)){
            addNewTab(object, new EntityView(object));
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

    public @Optional ObjectView getSelectedTab(){
        if(getSelectedIndex() != -1){
            return getTab(getSelectedIndex());
        } else {
            return null;
        }
    }

    public @Mandatory ObjectView getTab(int i){
        return (ObjectView) getComponentAt(i);
    }

    private void addNewTab(@Mandatory Object object, @Mandatory ObjectView view){
        addTab(null, null, view);
        setTabComponentAt(getTabCount() - 1, createTabHeader(object, view));
        setSelectedIndex(getTabCount() - 1);
    }

    private @Mandatory UiPanel createTabHeader(@Mandatory Object object, @Mandatory Component component) {
        UiPanel header = new UiPanel(0, PADDING, MIDDLE);

        UiLabel label = createTabHeaderLabel(object);
        label.addMouseListener(new MouseClickUserEventHandler(event -> selectTab(header)));
        label.addFocusListener(new FocusGainedUserEventHandler(() -> selectTab(header)));
        header.add(label, 0, 0, 0, 0, MIDDLE, BOTH);

        UiButton closeButton = new UiButton(null, "x", "Close", () -> remove(component));
        closeButton.setForeground(new Color(180, 180, 180, 255));
        header.add(closeButton, 1, 0, 0, 0, MIDDLE, NONE);

        header.rebuild();
        return header;
    }

    private @Mandatory UiLabel createTabHeaderLabel(@Mandatory Object object){
        return new UiLabel(
            new ObjectIconProvider().get(object),
            new ObjectNameProvider().get(object)
        );
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
            getTab(i).refresh();
        }
    }
}
