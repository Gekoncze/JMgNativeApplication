package cz.mg.nativeapplication.gui.components.controls;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.nativeapplication.gui.components.controls.value.UiFieldFactory;
import cz.mg.nativeapplication.gui.components.controls.value.UiValueField;
import cz.mg.nativeapplication.gui.components.other.ColorUtilities;
import cz.mg.nativeapplication.gui.event.MouseClickUserEventHandler;
import cz.mg.nativeapplication.gui.services.ObjectIconProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;


public class UiList extends UiVerticalPanel implements UiComponent {
    private static final Color FOCUSED_SELECTION_COLOR = UiConstants.getListSelectionBackgroundColor();
    private static final Color UNFOCUSED_SELECTION_COLOR = ColorUtilities.grayscale(FOCUSED_SELECTION_COLOR);
    private static final int BORDER = 2;
    private static final int PADDING = 2;

    private final @Mandatory @Shared ObjectIconProvider objectIconProvider = new ObjectIconProvider();

    private final @Mandatory @Part UiFieldFactory fieldFactory;
    private final @Mandatory @Part List<UiValueField> fields = new List<>();
    private @Optional Integer selectedIndex;
    private final List<KeyListener> itemKeyListeners = new List<>();
    private final List<MouseListener> itemMouseListeners = new List<>();
    private final List<FocusListener> itemFocusListeners = new List<>();

    public UiList(@Mandatory UiFieldFactory fieldFactory) {
        super(0, 0, Alignment.TOP);
        this.fieldFactory = fieldFactory;
        setBorder(BorderFactory.createEtchedBorder());
        setOpaque(true);
        setBackground(UiConstants.getListBackgroundColor());
        setRows(new List());
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
        this.fields.clear();
        this.clear();

        for(Object value : values){
            UiValueField field = createValueField(value);
            this.fields.addLast(field);
            this.add(createValueComponent(field, value), 1, 0, Alignment.LEFT, Fill.BOTH);
        }

        if(values.isEmpty()){
            this.add(createDummyField(), 1, 0, Alignment.LEFT, Fill.BOTH);
        }

        setSelectedIndex(selectedIndex);

        this.rebuild();
    }

    private UiHorizontalPanel createValueComponent(UiValueField field, Object value){
        UiHorizontalPanel panel = new UiHorizontalPanel(BORDER, PADDING, Alignment.LEFT);
        if(value != null){
            FontMetrics fontMetrics = getFontMetrics(field.getFont());
            UiImage image = new UiImage(objectIconProvider.getImageOptional(value));
            image.setPreferredSize(new Dimension(fontMetrics.getHeight(), fontMetrics.getHeight()));
            panel.add(image, 0, 0, Alignment.LEFT, Fill.BOTH);
        }
        panel.add(field, 1, 0, Alignment.LEFT, Fill.BOTH);
        panel.rebuild();
        return panel;
    }

    private UiValueField createValueField(Object value){
        UiValueField field = fieldFactory.create();
        field.setValue(value);
        field.addMouseListener(new MouseClickUserEventHandler(event -> {
            onItemSelected(field);
        }));
        field.setOpaque(false);
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
        return field;
    }

    private UiLabel createDummyField(){
        return new UiLabel("");
    }

    private void onItemSelected(UiValueField selectedField) {
        int i = 0;
        for(UiValueField field : fields){
            if(selectedField == field){
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
            if(fields.isEmpty()){
                selectedIndex = null;
            } else {
                if(index < 0) index = 0;
                if(index > (fields.count() - 1)) index = fields.count() - 1;
                selectedIndex = index;
            }
        }
        repaint();
    }

    private boolean childHasFocus(){
        Component component = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
        while(component != null){
            if(component == this){
                return true;
            }
            component = component.getParent();
        }
        return false;
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
            g.fillRect(component.getX(), component.getY(), component.getWidth(), component.getHeight());
        }
    }
}
