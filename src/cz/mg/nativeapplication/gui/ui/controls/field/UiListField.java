package cz.mg.nativeapplication.gui.ui.controls.field;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Shared;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.gui.event.MouseClickUserEventHandler;
import cz.mg.nativeapplication.gui.ui.UiConstants;
import cz.mg.nativeapplication.gui.ui.controls.UiText;
import cz.mg.nativeapplication.gui.ui.controls.field.base.UiFieldBase;
import cz.mg.nativeapplication.gui.ui.controls.field.other.UiFieldBaseFactory;
import cz.mg.nativeapplication.gui.ui.controls.field.other.UiFieldBaseWrapper;
import cz.mg.nativeapplication.gui.ui.controls.field.other.UiFieldImageProvider;
import cz.mg.nativeapplication.gui.ui.UiColorUtilities;

import java.awt.*;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;


public @Utility class UiListField extends UiField {
    private static final Color FOCUSED_SELECTION_COLOR = UiConstants.getListSelectionBackgroundColor();
    private static final Color UNFOCUSED_SELECTION_COLOR = UiColorUtilities.grayscale(FOCUSED_SELECTION_COLOR);

    private final @Mandatory @Shared UiFieldImageProvider fieldImageProvider;
    private final @Mandatory @Shared UiFieldBaseFactory fieldBaseFactory;

    private final @Mandatory @Part List<UiFieldBaseWrapper> fields = new List<>();

    private final @Mandatory @Shared List<KeyListener> itemKeyListeners = new List<>();
    private final @Mandatory @Shared List<MouseListener> itemMouseListeners = new List<>();
    private final @Mandatory @Shared List<FocusListener> itemFocusListeners = new List<>();

    private @Optional @Value Integer selectedIndex;

    public UiListField(
        @Mandatory UiFieldImageProvider fieldImageProvider,
        @Mandatory UiFieldBaseFactory fieldBaseFactory
    ) {
        this.fieldImageProvider = fieldImageProvider;
        this.fieldBaseFactory = fieldBaseFactory;
        setValues(new List());
    }

    public @Mandatory List<UiFieldBaseWrapper> getFields() {
        return fields;
    }

    public void addItemKeyListener(@Mandatory KeyListener listener){
        itemKeyListeners.addLast(listener);
    }

    public void addItemMouseListener(@Mandatory MouseListener listener){
        itemMouseListeners.addLast(listener);
    }

    public void addItemFocusListener(@Mandatory FocusListener listener){
        itemFocusListeners.addLast(listener);
    }

    public @Optional Integer getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(@Optional Integer index){
        if(index == null){
            selectedIndex = null;
        } else {
            if(fields.isEmpty()){
                selectedIndex = null;
            } else {
                if(index < 0){
                    selectedIndex = 0;
                } else if(index > (fields.count() - 1)){
                    selectedIndex = fields.count() - 1;
                } else {
                    selectedIndex = index;
                }
            }
        }
        repaint();
    }

    public @Mandatory List getValues(){
        List values = new List();
        for(UiFieldBaseWrapper fieldBaseWrapper : fields){
            values.addLast(fieldBaseWrapper.getValue());
        }
        return values;
    }

    public void setValues(@Mandatory List values){
        clear();
        fields.clear();

        for(Object value : values){
            fields.addLast(createField(value));
            addVertical(fields.getLast(), 1, 0, Alignment.LEFT, Fill.BOTH);
        }

        if(values.isEmpty()){
            addVertical(new UiText(""), 1, 0, Alignment.LEFT, Fill.BOTH);
        }

        setSelectedIndex(selectedIndex);

        rebuild();
    }

    private @Mandatory UiFieldBaseWrapper createField(@Optional Object value){
        UiFieldBase fieldBase = fieldBaseFactory.create();
        UiFieldBaseWrapper field = new UiFieldBaseWrapper(fieldImageProvider, fieldBase);
        field.setValue(value);

        fieldBase.addMouseListener(new MouseClickUserEventHandler(event -> { // TODO - might need special handling for wrapper
            selectField(field);
        }));

        for(KeyListener keyListener : itemKeyListeners){ // TODO - might need special handling for wrapper
            fieldBase.addKeyListener(keyListener);
        }

        for(MouseListener mouseListener : itemMouseListeners){ // TODO - might need special handling for wrapper
            fieldBase.addMouseListener(mouseListener);
        }

        for(FocusListener focusListener : itemFocusListeners){ // TODO - might need special handling for wrapper
            fieldBase.addFocusListener(focusListener);
        }

        return field;
    }

    private void selectField(@Mandatory UiFieldBaseWrapper targetField) {
        int i = 0;
        for(UiFieldBaseWrapper field : fields){
            if(targetField == field){
                setSelectedIndex(i);
                break;
            }
            i++;
        }
    }

    @Override
    protected void paintComponent(@Mandatory Graphics g) {
        super.paintComponent(g);
        if(selectedIndex != null){
            Component component = getComponent(selectedIndex);
            if(childHasFocus()){
                g.setColor(FOCUSED_SELECTION_COLOR);
            } else {
                g.setColor(UNFOCUSED_SELECTION_COLOR);
            }
            g.fillRect(
                component.getX() - BORDER,
                component.getY(),
                component.getWidth() + 2*BORDER,
                component.getHeight()
            );
        }
    }
}
