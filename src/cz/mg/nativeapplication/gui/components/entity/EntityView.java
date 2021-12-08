package cz.mg.nativeapplication.gui.components.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Shared;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClassProvider;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.ui.controls.*;
import cz.mg.nativeapplication.gui.components.entity.value.EntityBooleanValueSelect;
import cz.mg.nativeapplication.gui.components.entity.value.EntityEnumValueSelect;
import cz.mg.nativeapplication.gui.components.entity.value.EntityIntegerValueSelect;
import cz.mg.nativeapplication.gui.components.entity.value.EntityStringValueSelect;
import cz.mg.nativeapplication.gui.components.other.ObjectView;
import cz.mg.nativeapplication.gui.components.other.Refreshable;

import java.awt.event.KeyEvent;

import static cz.mg.nativeapplication.gui.ui.controls.UiPanel.Alignment.*;
import static cz.mg.nativeapplication.gui.ui.controls.UiPanel.Fill.*;


public @Utility class EntityView extends ObjectView {
    private static final int BORDER = 4;
    private static final int PADDING = 4;
    private static final int BUTTON_PADDING = 2;

    private final @Mandatory @Link Object entity;
    private final @Mandatory @Part List<EntitySelect> selects = new List<>();
    private final @Mandatory @Shared UiPanel panel = new UiPanel(BORDER, PADDING, TOP);

    private final @Mandatory @Shared EntityClassProvider entityClassProvider = new EntityClassProvider();

    public EntityView(@Mandatory Object entity) {
        this.entity = entity;

        EntityClass entityClass = entityClassProvider.get(entity.getClass());
        int y = 0;
        for(EntityField entityField : entityClass.getFields()){
            addSelect(createSelect(entity, entityField), y++);
        }

        panel.rebuild();
        setViewportView(panel);
    }

    private @Mandatory EntitySelect createSelect(@Mandatory Object entity, @Mandatory EntityField entityField){
        if(isList(entityField)){
            return new EntityPartSelect(entity, entityField);
        }

        if(isLink(entityField)){
            return new EntityLinkSelect(entity, entityField);
        }

        if(isPart(entityField)){
            return new EntityPartSelect(entity, entityField);
        }

        if(isValue(entityField)){
            if(is(entityField, String.class)){
                return new EntityStringValueSelect(entity, entityField);
            }

            if(is(entityField, Integer.class)){
                return new EntityIntegerValueSelect(entity, entityField);
            }

            if(is(entityField, Boolean.class)){
                return new EntityBooleanValueSelect(entity, entityField);
            }

            if(isEnum(entityField)){
                return new EntityEnumValueSelect(entity, entityField);
            }
        }

        throw new UnsupportedOperationException(); // TODO
    }

    private boolean isLink(@Mandatory EntityField entityField){
        return entityField.isAnnotationPresent(Link.class);
    }

    private boolean isPart(@Mandatory EntityField entityField){
        return entityField.isAnnotationPresent(Part.class);
    }

    private boolean isValue(@Mandatory EntityField entityField){
        return entityField.isAnnotationPresent(Value.class);
    }

    private boolean is(@Mandatory EntityField entityField, @Mandatory Class clazz){
        return clazz.isAssignableFrom(entityField.getField().getType());
    }

    private boolean isList(@Mandatory EntityField entityField){
        return is(entityField, List.class);
    }

    private boolean isEnum(@Mandatory EntityField entityField){
        return is(entityField, Enum.class);
    }

    private void addSelect(@Mandatory EntitySelect select, int y){
        panel.add(select.getLabel(), 0, y, 0, 0, MIDDLE, BOTH);
        panel.add(select.getContent().getField(), 1, y, 1, 0, MIDDLE, BOTH);
        panel.add(wrapButtons(select.getButtons()), 2, y, 0, 0, MIDDLE, BOTH);
        selects.addLast(select);
    }

    private @Mandatory UiHorizontalPanel wrapButtons(@Mandatory List<UiButton> buttons){
        UiHorizontalPanel panel = new UiHorizontalPanel(0, BUTTON_PADDING, LEFT);
        int x = 0;
        for(UiButton button : buttons){
            panel.add(button, x, 0, 0, 0, MIDDLE, BOTH);
            x++;
        }
        panel.rebuild();
        return panel;
    }

    @Override
    public void refresh() {
        for(Refreshable field : selects){
            field.refresh();
        }
    }

    @Override
    public Object getObject() {
        return entity;
    }

    @Override
    public void onKeyEvent(KeyEvent e) {
    }
}
