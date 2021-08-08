package cz.mg.nativeapplication.gui.components.entity.single.link;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.ToStringBuilder;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.entities.mg.components.MgComponent;
import cz.mg.nativeapplication.gui.components.MainWindow;
import cz.mg.nativeapplication.gui.components.controls.*;
import cz.mg.nativeapplication.gui.components.entity.single.EntitySingleSelect;
import cz.mg.nativeapplication.gui.handlers.ActionUserEventHandler;
import cz.mg.nativeapplication.gui.handlers.FocusGainedUserEventHandler;
import cz.mg.nativeapplication.gui.handlers.FocusLostUserEventHandler;
import cz.mg.nativeapplication.gui.handlers.KeyTypedUserEventHandler;
import cz.mg.nativeapplication.gui.icons.IconGallery;
import cz.mg.nativeapplication.gui.other.NavigationCache;
import cz.mg.nativeapplication.history.SetEntityFieldAction;
import cz.mg.nativeapplication.sevices.EntityField;
import cz.mg.nativeapplication.sevices.gui.ComponentSearch;
import cz.mg.nativeapplication.sevices.gui.ObjectNameProvider;

import javax.swing.*;
import java.awt.event.KeyEvent;

import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Alignment.LEFT;
import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Alignment.MIDDLE;
import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Fill.BOTH;


public @Utility class EntityFieldLinkSelect implements EntitySingleSelect {
    private static final int PADDING = 2;

    private final @Mandatory @Link MainWindow mainWindow;
    private final @Mandatory @Link Object entity;
    private final @Mandatory @Link EntityField entityField;

    private final @Mandatory @Shared UiLabel label;
    private final @Mandatory @Shared UiTextField textField;
    private final @Mandatory @Shared UiPanel buttons;
    private final @Mandatory @Shared UiButton clearButton;
    private final @Mandatory @Shared UiButton searchButton;
    private final @Mandatory @Shared UiPopupMenu popupMenu;

    public EntityFieldLinkSelect(
        @Mandatory MainWindow mainWindow,
        @Mandatory Object entity,
        @Mandatory EntityField entityField
    ) {
        this.mainWindow = mainWindow;
        this.entity = entity;
        this.entityField = entityField;
        this.label = new UiLabel(entityField.getName());
        this.textField = new UiTextField();
        this.textField.addFocusListener(new FocusGainedUserEventHandler(this::onFocusGained));
        this.textField.addFocusListener(new FocusLostUserEventHandler(this::onFocusLost));
        this.textField.addKeyListener(new KeyTypedUserEventHandler(this::onKeyTyped));
        this.clearButton = new UiButton(mainWindow, IconGallery.CLEAR, null, "Clear", this::onClearButtonClicked);
        this.searchButton = new UiButton(mainWindow, IconGallery.SEARCH, null, "Search (ctrl+space)", this::onSearchButtonClicked);
        this.buttons = new UiPanel(0, PADDING, LEFT);
        this.buttons.add(clearButton, 0, 0, 0, 0, MIDDLE, BOTH);
        this.buttons.add(searchButton, 1, 0, 0, 0, MIDDLE, BOTH);
        this.buttons.rebuild();
        this.popupMenu = new UiPopupMenu();
        refresh();
    }

    @Override
    public @Mandatory UiLabel getLabel() {
        return label;
    }

    @Override
    public @Mandatory UiTextField getContent() {
        return textField;
    }

    @Override
    public @Mandatory UiPanel getButtons() {
        return buttons;
    }

    @Override
    public void refresh() {
        Object object = entityField.get(entity);
        textField.setText(new ObjectNameProvider().getDisplayName(object));
        textField.setNull(object == null);
    }

    private void setValue(@Optional Object value) {
        mainWindow.getApplicationState().getHistory().run(new SetEntityFieldAction(
            entityField, entity, value, entityField.get(entity)
        ));
        refresh();
    }

    private void onFocusGained(){
        textField.selectAll();
    }

    private void onFocusLost(){
        if(!popupMenu.isVisible()){
            refresh();
        }
    }

    private void onKeyTyped(KeyEvent e){
        if(e.getKeyChar() == ' ' && e.isControlDown()){
            showComponentSelectionMenu();
            e.consume();
        }
    }

    private void onClearButtonClicked(){
        setValue(null);
    }

    private void onSearchButtonClicked(){
        showComponentSelectionMenu();
    }

    private void showComponentSelectionMenu(){
        popupMenu.removeAll();

        List<MgComponent> results = new ComponentSearch().search(
            mainWindow.getNavigationCache(),
            entityField.getField().getType(),
            textField.getText()
        );

        for(MgComponent result : results){
            JMenuItem item = new JMenuItem();
            item.addActionListener(new ActionUserEventHandler(() -> setValue(result)));
            item.setText(findComponentPath(mainWindow.getNavigationCache(), result));
            popupMenu.add(item);
        }

        if(results.count() < 1){
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
        NavigationCache.Node current = navigationCache.get(component);
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
