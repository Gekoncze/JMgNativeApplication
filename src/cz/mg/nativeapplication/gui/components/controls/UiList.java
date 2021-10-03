package cz.mg.nativeapplication.gui.components.controls;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.nativeapplication.gui.handlers.MouseClickUserEventHandler;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;


public class UiList extends UiVerticalPanel implements UiComponent {
    private final @Mandatory @Link List<Object> values = new List<>();
    private final @Mandatory @Part List<UiTextField> textFields = new List<>();
    private @Mandatory @Part UiList.Renderer renderer;
    private @Optional Integer selectedIndex;
    private final List<KeyListener> itemKeyListeners = new List<>();
    private final List<MouseListener> itemMouseListeners = new List<>();

    public UiList(int border, int padding) {
        super(border, padding, Alignment.TOP);
        setBorder(BorderFactory.createEtchedBorder());
        setOpaque(true);
        setBackground(UiConstants.getListBackgroundColor());
        setRows(new List());
    }

    public @Mandatory ReadableList<Object> getValues() {
        return values;
    }

    public @Mandatory ReadableList<UiTextField> getTextFields() {
        return textFields;
    }

    public @Optional UiList.Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(@Optional UiList.Renderer renderer) {
        this.renderer = renderer;
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

    public void setRows(@Mandatory List values){
        this.values.clear();
        this.textFields.clear();
        this.clear();

        this.values.addCollectionLast(values);
        for(Object value : values){
            UiTextField textField = new UiTextField();
            textField.setText(renderer.getText(value));
            textField.setNull(value == null);
            textField.addMouseListener(new MouseClickUserEventHandler(event -> {
                onItemSelected(textField);
            }));
            textField.setBorder(null);
            textField.setBackground(null);
            for(KeyListener keyListener : itemKeyListeners){
                textField.addKeyListener(keyListener);
            }
            for(MouseListener mouseListener : itemMouseListeners){
                textField.addMouseListener(mouseListener);
            }
            this.textFields.addLast(textField);
            this.add(textField, 1, 0, Alignment.LEFT, Fill.BOTH);
        }

        if(values.isEmpty()){
            this.add(new UiLabel(""), 1, 0, Alignment.LEFT, Fill.BOTH);
        }

        setSelectedIndex(selectedIndex);

        this.rebuild();
    }

    private void onItemSelected(UiTextField selectedTextField) {
        int i = 0;
        for(UiTextField textField : textFields){
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
                for(UiTextField textField : textFields){
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

    public interface Renderer {
        String getText(Object object);
    }
}
