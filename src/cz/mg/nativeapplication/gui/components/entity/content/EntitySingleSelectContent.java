package cz.mg.nativeapplication.gui.components.entity.content;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClasses;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.components.controls.value.UiFieldFactory;
import cz.mg.nativeapplication.gui.components.controls.value.UiValueField;
import cz.mg.nativeapplication.mg.services.history.Actions;
import cz.mg.nativeapplication.mg.services.history.actions.SetEntityFieldAction;

import java.awt.*;


public @Utility class EntitySingleSelectContent extends EntitySelectContent {
    private final @Mandatory @Link Object entity;
    private final @Mandatory @Link EntityField entityField;
    private final @Mandatory @Part UiFieldFactory fieldFactory;
    private @Optional @Part UiValueField field;

    public EntitySingleSelectContent(
        @Mandatory Object entity,
        @Mandatory EntityField entityField,
        @Mandatory UiFieldFactory fieldFactory
    ) {
        this.entity = entity;
        this.entityField = entityField;
        this.fieldFactory = fieldFactory;
    }

    @Override
    public @Mandatory Object getParent() {
        return entity;
    }

    @Override
    public @Optional Integer getChildIndex() {
        int i = 0;
        EntityClass entityClass = EntityClasses.getRepository().get(entity.getClass());
        for(EntityField entityField : entityClass.getFields()){
            if(entityField == this.entityField){
                return i;
            }
            i++;
        }

        throw new IllegalArgumentException(
            "Could not find field '" + this.entityField.getName() + "' in class '" + entityClass.getName() + "'."
        );
    }

    @Override
    public @Mandatory Class getType() {
        return entityField.getField().getType();
    }

    @Override
    public @Mandatory String getName() {
        return entityField.getName();
    }

    @Override
    public final @Optional Object getValue(){
        return entityField.get(entity);
    }

    @Override
    public final void setValue(@Optional Object value) {
        Actions.run(
            new SetEntityFieldAction(entity, entityField, entityField.get(entity), value)
        );
        refresh();
    }

    @Override
    public @Optional UiValueField getField() {
        if(field == null){
            field = fieldFactory.create();
            refresh();
        }
        return field;
    }

    @Override
    public Component getComponent() {
        return getField();
    }

    @Override
    public void refresh(){
        if(field != null){
            field.setValue(getValue());
            field.lock();
        }
    }
}
