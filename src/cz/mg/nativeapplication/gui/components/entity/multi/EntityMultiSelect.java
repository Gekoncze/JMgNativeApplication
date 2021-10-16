package cz.mg.nativeapplication.gui.components.entity.multi;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.components.MainWindow;
import cz.mg.nativeapplication.gui.components.controls.UiList;
import cz.mg.nativeapplication.gui.components.entity.EntitySelect;
import cz.mg.nativeapplication.gui.services.ObjectNameProvider;
import cz.mg.nativeapplication.mg.services.history.AddListItemAction;
import cz.mg.nativeapplication.mg.services.history.RemoveListItemAction;
import cz.mg.nativeapplication.mg.services.history.SetListItemAction;
import cz.mg.nativeapplication.mg.services.history.Transaction;


public @Utility abstract class EntityMultiSelect extends EntitySelect {
    protected final @Mandatory @Link MainWindow mainWindow;
    protected final @Mandatory @Link Object entity;
    protected final @Mandatory @Link EntityField entityField;
    protected final @Mandatory @Link List list;

    private final @Mandatory @Shared ObjectNameProvider objectNameProvider = new ObjectNameProvider();

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

    protected final @Optional Object getValueAt(int i){
        return list.get(i);
    }

    @Override
    public final @Optional Object getValue(){
        Integer i = getContent().getSelectedIndex();
        if(i != null){
            return getValueAt(i);
        } else {
            return null;
        }
    }

    protected void setValueAt(int i, @Optional Object value){
        if(i >= 0 && i < list.count()){
            mainWindow.getApplicationState().getHistory().addTransaction().run(
                new SetListItemAction(list, i, getValueAt(i), value)
            );
            refresh();
        }
    }

    @Override
    public final void setValue(@Optional Object value){
        Integer i = getContent().getSelectedIndex();
        if(i != null){
            setValueAt(i, value);
        }
    }

    protected void addRowAt(int i){
        if(i >= 0 && i <= list.count()){
            mainWindow.getApplicationState().getHistory().addTransaction().run(
                new AddListItemAction(list, i, null)
            );
            refresh();
        }
    }

    protected void addRow(){
        Integer i = getContent().getSelectedIndex();
        if(i != null){
            addRowAt(i);
        } else {
            addRowAt(list.count());
        }
    }

    protected void removeRowAt(int i){
        if(i >= 0 && i < list.count()){
            mainWindow.getApplicationState().getHistory().addTransaction().run(
                new RemoveListItemAction(list, i, list.get(i))
            );
            refresh();
        }
    }

    protected void removeRow(){
        Integer i = getContent().getSelectedIndex();
        if(i != null){
            removeRowAt(i);
        }
    }

    protected void moveRow(int srcIndex, int dstIndex){
        if(srcIndex != dstIndex){
            if(srcIndex >= 0 && srcIndex < list.count()){
                if(dstIndex >= 0 && dstIndex < list.count()){
                    Object value = getValueAt(srcIndex);
                    Transaction transaction = mainWindow.getApplicationState().getHistory().addTransaction();
                    transaction.run(
                        new RemoveListItemAction(list, srcIndex, value)
                    );
                    transaction.run(
                        new AddListItemAction(list, dstIndex, value)
                    );
                    refresh();
                }
            }
        }
    }

    protected void moveRowUp(){
        Integer i = getContent().getSelectedIndex();
        if(i != null){
            moveRow(i, i - 1);
            getContent().setSelectedIndex(i - 1);
        }
    }

    protected void moveRowDown(){
        Integer i = getContent().getSelectedIndex();
        if(i != null){
            moveRow(i, i + 1);
            getContent().setSelectedIndex(i + 1);
        }
    }

    @Override
    public void refresh() {
        getContent().setRenderer(objectNameProvider::get);
        getContent().setRows(list);
    }
}
