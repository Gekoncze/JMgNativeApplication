package cz.mg.nativeapplication.gui.components.entity.content;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.components.controls.UiButton;
import cz.mg.nativeapplication.gui.components.controls.value.UiFieldFactory;
import cz.mg.nativeapplication.gui.components.controls.value.UiValueField;
import cz.mg.nativeapplication.gui.services.HistoryProvider;
import cz.mg.nativeapplication.mg.services.history.SetEntityFieldAction;

import java.awt.*;


public @Utility class EntitySingleSelectContent extends EntitySelectContent {
    private final @Mandatory @Shared HistoryProvider historyProvider = new HistoryProvider();

    private final @Mandatory @Link Object entity;
    private final @Mandatory @Link EntityField entityField;
    private final @Mandatory @Part UiFieldFactory fieldFactory;
    private final @Mandatory @Part UiValueField field;

    public EntitySingleSelectContent(
        @Mandatory Object entity,
        @Mandatory EntityField entityField,
        @Mandatory UiFieldFactory fieldFactory
    ) {
        this.entity = entity;
        this.entityField = entityField;
        this.fieldFactory = fieldFactory;
        this.field = fieldFactory.create();
    }

    @Override
    public Object getEntity() {
        return entity;
    }

    @Override
    public EntityField getEntityField() {
        return entityField;
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
        historyProvider.get().addTransaction().run(
            new SetEntityFieldAction(
                entity, entityField, entityField.get(entity), value
            )
        );
        refresh();
    }

    @Override
    public @Optional UiValueField getField() {
        return field;
    }

    @Override
    public Component getComponent() {
        return field;
    }

    @Override
    public List<UiButton> getButtons() {
        return new List<>();
    }

    @Override
    public void refresh(){
        field.setValue(getValue());
        field.lock();
    }
}
