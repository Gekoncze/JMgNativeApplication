package cz.mg.nativeapplication.gui.components.entity.content;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClassProvider;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.services.ObjectImageProvider;
import cz.mg.nativeapplication.gui.ui.controls.field.UiField;
import cz.mg.nativeapplication.gui.ui.controls.field.UiValueField;
import cz.mg.nativeapplication.gui.ui.controls.field.other.UiFieldBaseFactory;
import cz.mg.nativeapplication.gui.ui.controls.field.other.UiFieldBaseWrapper;
import cz.mg.nativeapplication.mg.services.history.Actions;
import cz.mg.nativeapplication.mg.services.history.actions.SetEntityFieldAction;


public @Utility class EntitySingleSelectContent extends EntitySelectContent {
    private final @Mandatory @Shared EntityClassProvider entityClassProvider = new EntityClassProvider();
    private final @Mandatory @Shared ObjectImageProvider objectImageProvider = new ObjectImageProvider();

    private final @Mandatory @Link Object entity;
    private final @Mandatory @Link EntityField entityField;
    private final @Mandatory @Part UiFieldBaseFactory fieldBaseFactory;
    private @Optional @Part UiValueField field;

    public EntitySingleSelectContent(
        @Mandatory Object entity,
        @Mandatory EntityField entityField,
        @Mandatory UiFieldBaseFactory fieldBaseFactory
    ) {
        this.entity = entity;
        this.entityField = entityField;
        this.fieldBaseFactory = fieldBaseFactory;
    }

    @Override
    public @Mandatory Object getParent() {
        return entity;
    }

    @Override
    public @Optional Integer getChildIndex() {
        int i = 0;
        EntityClass entityClass = entityClassProvider.get(entity.getClass());
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
    public @Optional UiFieldBaseWrapper getFieldBase() {
        return ((UiValueField)getField()).getField();
    }

    @Override
    public @Mandatory UiField getField() {
        if(field == null){
            field = new UiValueField(objectImageProvider::getOptional, fieldBaseFactory);
            refresh();
        }
        return field;
    }

    @Override
    public void refresh(){
        if(field != null){
            field.setValue(getValue());
            field.lock();
        }
    }

    @Override
    public void softRefresh() {
        refresh();
    }
}
