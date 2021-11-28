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
            EntitySelectType type = isList(entityField)
                ? EntitySelectType.MULTI_SELECT
                : EntitySelectType.SINGLE_SELECT;

            if(isLink(entityField)){
                addSelect(new EntityLinkSelect(entity, entityField, type), y++, type);
            }

            if(isPart(entityField)){
                addSelect(new EntityPartSelect(entity, entityField, type), y++, type);
            }

            if(isValue(entityField)){
                if(is(entityField, String.class)){
                    addSelect(new EntityStringValueSelect(entity, entityField, type), y++, type);
                }

                if(is(entityField, Integer.class)){
                    addSelect(new EntityIntegerValueSelect(entity, entityField, type), y++, type);
                }

                if(is(entityField, Boolean.class)){
                    addSelect(new EntityBooleanValueSelect(entity, entityField, type), y++, type);
                }

                if(isEnum(entityField)){
                    addSelect(new EntityEnumValueSelect(entity, entityField, type), y++, type);
                }
            }
        }

        panel.rebuild();
        setViewportView(panel);
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

    private void addSelect(@Mandatory EntitySelect select, int y, @Mandatory EntitySelectType type){
        switch (type){
            case SINGLE_SELECT:
                addSingleSelect(select, y);
                break;
            case MULTI_SELECT:
                addMultiSelect(select, y);
                break;
            default:
                throw new UnsupportedOperationException("Unsupported entity select type '" + type + "'.");
        }
    }

    private void addSingleSelect(@Mandatory EntitySelect select, int y){
        panel.add(select.getLabel(), 0, y, 0, 0, MIDDLE, BOTH);
        panel.add(select.getContent().getField(), 1, y, 1, 0, MIDDLE, BOTH);
        panel.add(wrapButtons(select.getButtons()), 2, y, 0, 0, MIDDLE, BOTH);
        selects.addLast(select);
    }

    private void addMultiSelect(@Mandatory EntitySelect select, int y){
        UiHorizontalPanel horizontalPanel = new UiHorizontalPanel(0, PADDING, LEFT);
        horizontalPanel.add(select.getLabel(), 0, 0, 1, 0, LEFT, HORIZONTAL);
        horizontalPanel.add(wrapButtons(select.getButtons()), 1, 0, 0, 0, RIGHT, NONE);
        horizontalPanel.rebuild();

        UiVerticalPanel verticalPanel = new UiVerticalPanel(0, PADDING, TOP);
        verticalPanel.add(horizontalPanel, 0, 0, 1, 0, LEFT, HORIZONTAL);
        verticalPanel.add(select.getContent().getField(), 0, 1, 1, 1, MIDDLE, BOTH);
        verticalPanel.rebuild();

        panel.add(verticalPanel, 0, y, 1, 0, MIDDLE, BOTH, 3, 1);
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
