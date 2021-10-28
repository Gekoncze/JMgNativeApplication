package cz.mg.nativeapplication.gui.components.controls;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.nativeapplication.gui.components.controls.value.UiFieldFactory;
import cz.mg.nativeapplication.gui.components.controls.value.UiValueField;
import cz.mg.nativeapplication.gui.event.MouseClickUserEventHandler;

import javax.swing.*;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;


public class UiList extends UiVerticalPanel implements UiComponent {
    private final @Mandatory @Part UiFieldFactory fieldFactory;
    private final @Mandatory @Link List<Object> values = new List<>();
    private final @Mandatory @Part List<UiValueField> fields = new List<>();
    private @Optional Integer selectedIndex;
    private final List<KeyListener> itemKeyListeners = new List<>();
    private final List<MouseListener> itemMouseListeners = new List<>();
    private final List<FocusListener> itemFocusListeners = new List<>();

    public UiList(int border, int padding, @Mandatory UiFieldFactory fieldFactory) {
        super(border, padding, Alignment.TOP);
        this.fieldFactory = fieldFactory;
        setBorder(BorderFactory.createEtchedBorder());
        setOpaque(true);
        setBackground(UiConstants.getListBackgroundColor());
        setRows(new List());
    }

    public @Mandatory ReadableList<Object> getValues() {
        return values;
    }

    public @Mandatory ReadableList<UiValueField> getFields() {
        return fields;
    }

    public @Optional Integer getSelectedIndex() {
        return selectedIndex;
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

    public void setRows(@Mandatory List values){
        this.values.clear();
        this.fields.clear();
        this.clear();

        this.values.addCollectionLast(values);
        for(Object value : values){
            UiValueField field = fieldFactory.create();
            field.setValue(value);
            field.addMouseListener(new MouseClickUserEventHandler(event -> {
                onItemSelected(field);
            }));
            field.setBorder(null);
            field.setBackground(null);
            for(KeyListener keyListener : itemKeyListeners){
                field.addKeyListener(keyListener);
            }
            for(MouseListener mouseListener : itemMouseListeners){
                field.addMouseListener(mouseListener);
            }
            for(FocusListener focusListener : itemFocusListeners){
                field.addFocusListener(focusListener);
            }
            this.fields.addLast(field);
            this.add(field, 1, 0, Alignment.LEFT, Fill.BOTH);
        }

        if(values.isEmpty()){
            this.add(new UiLabel(""), 1, 0, Alignment.LEFT, Fill.BOTH);
        }

        setSelectedIndex(selectedIndex);

        this.rebuild();
    }

    private void onItemSelected(UiTextField selectedTextField) {
        int i = 0;
        for(UiTextField textField : fields){
            if(selectedTextField == textField){
                setSelectedIndex(i);
                break;
            }
            i++;
        }
    }

    public void setSelectedIndex(@Optional Integer index){
        if(index == null){
            selectedIndex = null;
        } else {
            if(values.isEmpty()){
                selectedIndex = null;
            } else {
                if(index < 0) index = 0;
                if(index > (values.count() - 1)) index = values.count() - 1;
                selectedIndex = index;

                int i = 0;
                for(UiTextField textField : fields){
                    if(i == index){
                        textField.setBorder(BorderFactory.createLineBorder(UiConstants.getListSelectionBackgroundColor()));
                    } else {
                        textField.setBorder(null);
                    }
                    i++;
                }
            }
        }
    }

}
