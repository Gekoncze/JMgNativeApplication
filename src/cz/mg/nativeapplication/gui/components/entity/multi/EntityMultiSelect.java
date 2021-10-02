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
        refresh();
    }

    protected final void removeValue(int i){
        mainWindow.getApplicationState().getHistory().run(
            new RemoveListItemAction(list, i, list.get(i))
        );
        refresh();
    }

    @Override
    public void refresh() {
        getContent().setRenderer(objectNameProvider::get);
        getContent().setRows(list);
    }
}
