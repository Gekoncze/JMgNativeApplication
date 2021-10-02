package cz.mg.nativeapplication.gui.components.entity.multi;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.components.MainWindow;
import cz.mg.nativeapplication.gui.components.controls.UiConstants;
import cz.mg.nativeapplication.gui.components.controls.UiLabel;
import cz.mg.nativeapplication.gui.components.controls.UiList;
import cz.mg.nativeapplication.gui.components.entity.EntitySelect;
import cz.mg.nativeapplication.gui.services.ObjectIconProvider;
import cz.mg.nativeapplication.gui.services.ObjectNameProvider;
import cz.mg.nativeapplication.mg.services.history.AddListItemAction;
import cz.mg.nativeapplication.mg.services.history.RemoveListItemAction;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.*;


public @Utility abstract class EntityMultiSelect extends EntitySelect {
    protected final @Mandatory @Link MainWindow mainWindow;
    protected final @Mandatory @Link Object entity;
    protected final @Mandatory @Link EntityField entityField;
    protected final @Mandatory @Link List list;

    private final @Mandatory @Shared ObjectNameProvider objectNameProvider = new ObjectNameProvider();
    private final @Mandatory @Shared ObjectIconProvider objectIconProvider = new ObjectIconProvider();

    public EntityMultiSelect(
        @Mandatory MainWindow mainWindow,
        @Mandatory Object entity,
        @Mandatory EntityField entityField
    ) {
        this.mainWindow = mainWindow;
        this.entity = entity;
        this.entityField = entityField;
        this.list = (List) entityField.get(entity);
    }

    @Override
    public abstract @Mandatory UiList getContent();

    protected final int valueCount(){
        return list.count();
    }

    protected final @Optional Object getValue(int i){
        return list.get(i);
    }

    protected final void addValue(int i, @Optional Object value){
        mainWindow.getApplicationState().getHistory().run(
            new AddListItemAction(list, i, value)
        );
    }

    protected final void removeValue(int i){
        mainWindow.getApplicationState().getHistory().run(
            new RemoveListItemAction(list, i, list.get(i))
        );
    }

    @Override
    public void refresh() {
        getContent().setModel(new ObjectListModel());
        getContent().setCellRenderer(new ObjectListRenderer());
    }

    protected abstract @Mandatory Object createValue();
    protected abstract void editValue(@Mandatory Object object);

    private @Utility class ObjectListModel implements ListModel {
        @Override
        public int getSize() {
            return list.count();
        }

        @Override
        public Object getElementAt(int i) {
            return list.get(i);
        }

        @Override
        public void addListDataListener(ListDataListener listDataListener) {
        }

        @Override
        public void removeListDataListener(ListDataListener listDataListener) {
        }
    }

    private @Utility class ObjectListRenderer implements ListCellRenderer {

        @Override
        public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean selected,
            boolean hasFocus
        ) {
            UiLabel label = new UiLabel(
                objectIconProvider.get(value),
                objectNameProvider.get(value)
            );

            if(selected){
                label.setOpaque(true);
                label.setBackground(UiConstants.getListSelectionBackgroundColor());
            }

            return label;
        }
    }
}
