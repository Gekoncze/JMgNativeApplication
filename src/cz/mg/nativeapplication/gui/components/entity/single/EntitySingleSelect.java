package cz.mg.nativeapplication.gui.components.entity.single;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.components.MainWindow;
import cz.mg.nativeapplication.gui.components.entity.EntitySelect;
import cz.mg.nativeapplication.gui.history.SetEntityFieldAction;


public @Utility abstract class EntitySingleSelect extends EntitySelect {
    protected final @Mandatory @Link MainWindow mainWindow;
    protected final @Mandatory @Link Object entity;
    protected final @Mandatory @Link EntityField entityField;

    public EntitySingleSelect(
        @Mandatory MainWindow mainWindow,
        @Mandatory Object entity,
        @Mandatory EntityField entityField
    ) {
        this.mainWindow = mainWindow;
        this.entity = entity;
        this.entityField = entityField;
    }

    protected final @Optional Object getValue(){
        return entityField.get(entity);
    }

    protected final void setValue(@Optional Object value) {
        mainWindow.getApplicationState().getHistory().run(
            new SetEntityFieldAction(
                entityField, entity, value, entityField.get(entity)
            )
        );
        refresh();
    }
}
