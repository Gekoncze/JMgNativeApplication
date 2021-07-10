package cz.mg.nativeapplication.gui.components.part;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.ToStringBuilder;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.entities.mg.components.MgComponent;
import cz.mg.nativeapplication.gui.MainWindow;
import cz.mg.nativeapplication.gui.handlers.ActionUserEventHandler;
import cz.mg.nativeapplication.gui.handlers.FocusGainedUserEventHandler;
import cz.mg.nativeapplication.gui.handlers.FocusLostUserEventHandler;
import cz.mg.nativeapplication.gui.handlers.KeyTypedUserEventHandler;
import cz.mg.nativeapplication.gui.utilities.NavigationCache;
import cz.mg.nativeapplication.sevices.gui.ComponentSearch;

import javax.swing.*;
import java.awt.event.KeyEvent;

import static cz.mg.nativeapplication.gui.utilities.NavigationCache.Node;


public @Utility class ComponentLinkSelect extends JTextField {
    private final @Mandatory MainWindow mainWindow;
    private final @Mandatory Class typeFilter;
    private @Optional MgComponent selectedComponent;

    public ComponentLinkSelect(@Mandatory MainWindow mainWindow, @Mandatory Class typeFilter) {
        this.mainWindow = mainWindow;
        this.typeFilter = typeFilter;
        addFocusListener(new FocusGainedUserEventHandler(mainWindow, this::onFocusGained));
        addFocusListener(new FocusLostUserEventHandler(mainWindow, this::onFocusLost));
        addKeyListener(new KeyTypedUserEventHandler(mainWindow, this::onKeyTyped));
    }

    private void onFocusGained(){
        setText("");
    }

    private void onFocusLost(){
        setText(getComponentDisplayName(selectedComponent));
        // todo - is there a way to prevent focus lost when popup menu is opened?
    }

    private void onKeyTyped(KeyEvent e){
        if(e.getKeyChar() == ' ' && e.isControlDown()){
            showComponentSelectionMenu();
            e.consume();
        }
    }

    public MgComponent getSelectedComponent() {
        return selectedComponent;
    }

    private static @Mandatory String getComponentDisplayName(@Optional MgComponent component){
        if(component != null){
            if(component.name != null){
                if(component.name.trim().length() > 0){
                    return component.name;
                } else {
                    return component.getClass().getSimpleName();
                }
            } else {
                return component.getClass().getSimpleName();
            }
        } else {
            return "";
        }
    }

    private void showComponentSelectionMenu(){
        JPopupMenu menu = new JPopupMenu();
        String nameFilter = getText();
        List<MgComponent> components = new ComponentSearch().search(
            mainWindow.getNavigationCache(), typeFilter, nameFilter
        );

        for(MgComponent component : components){
            JMenuItem item = new JMenuItem();
            item.addActionListener(new ActionUserEventHandler(mainWindow, () -> selectedComponent = component));
            item.setText(findComponentPath(mainWindow.getNavigationCache(), component));
            menu.add(item);
        }

        if(components.count() < 1){
            JMenuItem item = new JMenuItem();
            item.setText("No results.");
            item.setEnabled(false);
            menu.add(item);
        }

        menu.show(ComponentLinkSelect.this, 0, getHeight());
    }

    private @Mandatory String findComponentPath(
        @Mandatory NavigationCache navigationCache,
        @Mandatory MgComponent component
    ){
        List<MgComponent> path = new List<>();
        Node current = navigationCache.get(component);
        while(current != null){
            if(current.getSelf() instanceof MgComponent){
                path.addFirst((MgComponent) current.getSelf());
            }
            current = current.getParent();
        }
        path.removeFirst(); // remove root location
        return new ToStringBuilder<>(path).convert(c -> c.name).delim(".").build();
    }
}
