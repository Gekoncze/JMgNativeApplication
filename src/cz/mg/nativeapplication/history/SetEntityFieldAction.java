package cz.mg.nativeapplication.history;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.nativeapplication.sevices.EntityField;


public @Utility class SetEntityFieldAction implements Action {
    private final @Mandatory @Link EntityField entityField;
    private final @Mandatory @Link Object entity;
    private final @Optional @Link Object newValue;
    private final @Optional @Link Object oldValue;

    public SetEntityFieldAction(
        @Optional EntityField entityField,
        @Optional Object entity,
        @Optional Object newValue,
        @Optional Object oldValue
    ) {
        this.entityField = entityField;
        this.entity = entity;
        this.newValue = newValue;
        this.oldValue = oldValue;
    }

    @Override
    public void redo() {
        entityField.set(entity, newValue);
    }

    @Override
    public void undo() {
        entityField.set(entity, oldValue);
    }
}
