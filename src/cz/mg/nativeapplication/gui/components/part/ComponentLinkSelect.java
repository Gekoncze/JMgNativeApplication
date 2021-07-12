package cz.mg.nativeapplication.gui.components.part;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.ToStringBuilder;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.entities.mg.components.MgComponent;
import cz.mg.nativeapplication.gui.MainWindow;
import cz.mg.nativeapplication.gui.handlers.*;
import cz.mg.nativeapplication.gui.icons.IconGallery;
import cz.mg.nativeapplication.gui.utilities.NavigationCache;
import cz.mg.nativeapplication.sevices.gui.ComponentSearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static cz.mg.nativeapplication.gui.utilities.NavigationCache.Node;


public @Utility class ComponentLinkSelect {
    private final @Mandatory @Link MainWindow mainWindow;
    private final @Mandatory @Link Class typeFilter;
    private @Optional @Link MgComponent value;
    private @Optional @Link JPopupMenu popupMenu;
    private @Optional @Part ChangeUserEventHandler changeHandler;
    private final @Mandatory @Link JLabel label;
    private final @Mandatory @Link JTextField textField;
    private final @Mandatory @Link JButton clearButton;

    public ComponentLinkSelect(
        @Mandatory MainWindow mainWindow,
        @Mandatory Class typeFilter,
        @Mandatory String label,
        @Optional MgComponent value
    ) {
        this.mainWindow = mainWindow;
        this.typeFilter = typeFilter;
        this.label = new JLabel(label);
        this.textField = new JTextField();
        this.textField.addFocusListener(new FocusGainedUserEventHandler(mainWindow, this::onFocusGained));
        this.textField.addFocusListener(new FocusLostUserEventHandler(mainWindow, this::onFocusLost));
        this.textField.addKeyListener(new KeyTypedUserEventHandler(mainWindow, this::onKeyTyped));
        this.clearButton = new JButton(mainWindow.getIconGallery().getIcon(IconGallery.CLEAR));
        this.clearButton.setBackground(new Color(0, 0, 0, 0));
        this.clearButton.setBorder(null);
        this.clearButton.addActionListener(new ActionUserEventHandler(mainWindow, this::onClearButtonClicked));
        setValue(value);
    }

    public @Optional MgComponent getValue() {
        return value;
    }

    public void setValue(@Optional MgComponent value) {
        this.value = value;
        updateText();
        if(changeHandler != null){
            changeHandler.stateChanged();
        }
    }

    public ChangeUserEventHandler getChangeHandler() {
        return changeHandler;
    }

    public void setChangeHandler(ChangeUserEventHandler changeHandler) {
        this.changeHandler = changeHandler;
    }

    public @Mandatory JLabel getLabel() {
        return label;
    }

    public @Mandatory JTextField getTextField() {
        return textField;
    }

    public @Mandatory JButton getClearButton() {
        return clearButton;
    }

    private void onFocusGained(){
        textField.selectAll();
    }

    private void onFocusLost(){
        if(popupMenu == null){
            updateText();
        }
    }

    private void onKeyTyped(KeyEvent e){
        if(e.getKeyChar() == ' ' && e.isControlDown()){
            showComponentSelectionMenu();
            e.consume();
        }
    }

    private void onClearButtonClicked() {
        setValue(null);
    }

    private void updateText(){
        textField.setText(getComponentDisplayName(value));
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
        popupMenu = new JPopupMenu();
        popupMenu.addPopupMenuListener(new PopupMenuCloseUserEventHandler(mainWindow, () -> popupMenu = null));

        String nameFilter = textField.getText();
        List<MgComponent> components = new ComponentSearch().search(
            mainWindow.getNavigationCache(), typeFilter, nameFilter
        );

        for(MgComponent component : components){
            JMenuItem item = new JMenuItem();
            item.addActionListener(new ActionUserEventHandler(mainWindow, () -> setValue(component)));
            item.setText(findComponentPath(mainWindow.getNavigationCache(), component));
            popupMenu.add(item);
        }

        if(components.count() < 1){
            JMenuItem item = new JMenuItem();
            item.setText("No results.");
            item.setEnabled(false);
            popupMenu.add(item);
        }

        popupMenu.show(textField, 0, textField.getHeight());
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
