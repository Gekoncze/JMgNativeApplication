package cz.mg.nativeapplication.gui.components.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.collections.ToStringBuilder;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.entities.mg.components.MgComponent;
import cz.mg.nativeapplication.gui.MainWindow;
import cz.mg.nativeapplication.gui.components.RefreshableComponent;
import cz.mg.nativeapplication.gui.handlers.*;
import cz.mg.nativeapplication.gui.icons.IconGallery;
import cz.mg.nativeapplication.gui.utilities.GridSettingsFactory;
import cz.mg.nativeapplication.gui.utilities.NavigationCache;
import cz.mg.nativeapplication.history.SetFieldValueAction;
import cz.mg.nativeapplication.sevices.EntityField;
import cz.mg.nativeapplication.sevices.gui.ComponentSearch;
import cz.mg.nativeapplication.sevices.gui.ObjectNameProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public @Utility class EntityFieldLinkSelect implements RefreshableComponent {
    private static final int PADDING = 2;

    private final @Mandatory @Link MainWindow mainWindow;
    private @Optional @Link JPopupMenu popupMenu;
    private final @Mandatory @Link JLabel label;
    private final @Mandatory @Link JTextField textField;
    private final @Mandatory @Link JPanel buttons;
    private final @Mandatory @Link JButton clearButton;
    private final @Mandatory @Link JButton searchButton;

    private final @Mandatory Object entity;
    private final @Mandatory EntityField entityField;

    public EntityFieldLinkSelect(
        @Mandatory MainWindow mainWindow,
        @Mandatory Object entity,
        @Mandatory EntityField entityField
    ) {
        this.mainWindow = mainWindow;
        this.label = new JLabel(entityField.getName());
        this.textField = new JTextField();
        this.textField.addFocusListener(new FocusGainedUserEventHandler(mainWindow, this::onFocusGained));
        this.textField.addFocusListener(new FocusLostUserEventHandler(mainWindow, this::onFocusLost));
        this.textField.addKeyListener(new KeyTypedUserEventHandler(mainWindow, this::onKeyTyped));
        this.buttons = new JPanel();
        this.buttons.setLayout(new GridBagLayout());
        this.clearButton = new JButton(mainWindow.getIconGallery().getIcon(IconGallery.CLEAR));
        this.clearButton.addActionListener(new ActionUserEventHandler(mainWindow, this::onClearButtonClicked));
        this.clearButton.setToolTipText("Clear");
        this.clearButton.setBackground(new Color(0, 0, 0, 0));
        this.clearButton.setBorder(null);
        this.clearButton.setOpaque(false);
        this.searchButton = new JButton(mainWindow.getIconGallery().getIcon(IconGallery.SEARCH));
        this.searchButton.addActionListener(new ActionUserEventHandler(mainWindow, this::onSearchButtonClicked));
        this.searchButton.setToolTipText("Search (ctrl+space)");
        this.searchButton.setBackground(new Color(0, 0, 0, 0));
        this.searchButton.setBorder(null);
        this.searchButton.setOpaque(false);
        this.buttons.add(clearButton, new GridSettingsFactory().create(0, 0, 0, 0, 0, 0, 0, PADDING));
        this.buttons.add(searchButton, new GridSettingsFactory().create(1, 0, 0, 0, 0, PADDING, 0, 0));
        this.buttons.add(new JPanel(), new GridSettingsFactory().create(2, 0, 1, 0, 0)); // dummy alignment panel
        this.entity = entity;
        this.entityField = entityField;
        refresh();
    }

    public @Mandatory JLabel getLabel() {
        return label;
    }

    public @Mandatory JTextField getTextField() {
        return textField;
    }

    public @Mandatory JPanel getButtons() {
        return buttons;
    }

    @Override
    public void refresh() {
        textField.setText(new ObjectNameProvider().getDisplayName(entityField.get(entity)));
    }

    private void setValue(@Optional Object value) {
        mainWindow.getHistory().run(new SetFieldValueAction(
            entityField, entity, value, entityField.get(entity)
        ));
        refresh();
    }

    private void onFocusGained(){
        textField.selectAll();
    }

    private void onFocusLost(){
        if(popupMenu == null){
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
        popupMenu = new JPopupMenu();
        popupMenu.addPopupMenuListener(new PopupMenuCloseUserEventHandler(mainWindow, () -> popupMenu = null));

        List<MgComponent> results = new ComponentSearch().search(
            mainWindow.getNavigationCache(),
            entityField.getField().getType(),
            textField.getText()
        );

        for(MgComponent result : results){
            JMenuItem item = new JMenuItem();
            item.addActionListener(new ActionUserEventHandler(mainWindow, () -> setValue(result)));
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
