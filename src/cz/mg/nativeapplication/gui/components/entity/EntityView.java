package cz.mg.nativeapplication.gui.components.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Shared;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClassRepository;
import cz.mg.entity.EntityClasses;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.components.MainWindow;
import cz.mg.nativeapplication.gui.components.controls.UiButton;
import cz.mg.nativeapplication.gui.components.controls.UiHorizontalPanel;
import cz.mg.nativeapplication.gui.components.controls.UiPanel;
import cz.mg.nativeapplication.gui.components.controls.UiVerticalPanel;
import cz.mg.nativeapplication.gui.components.entity.multi.EntityMultiSelect;
import cz.mg.nativeapplication.gui.components.entity.multi.link.EntityLinkMultiSelect;
import cz.mg.nativeapplication.gui.components.entity.multi.part.EntityPartMultiSelect;
import cz.mg.nativeapplication.gui.components.entity.single.EntitySingleSelect;
import cz.mg.nativeapplication.gui.components.entity.single.link.EntityLinkSingleSelect;
import cz.mg.nativeapplication.gui.components.entity.single.part.EntityPartSingleSelect;
import cz.mg.nativeapplication.gui.components.entity.single.value.EntityBooleanValueSingleSelect;
import cz.mg.nativeapplication.gui.components.entity.single.value.EntityEnumValueSingleSelect;
import cz.mg.nativeapplication.gui.components.entity.single.value.EntityIntegerValueSingleSelect;
import cz.mg.nativeapplication.gui.components.entity.single.value.EntityStringValueSingleSelect;
import cz.mg.nativeapplication.gui.components.other.ObjectView;
import cz.mg.nativeapplication.gui.components.other.RefreshableView;

import java.awt.*;

import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Alignment.*;
import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Fill.*;


public @Utility class EntityView extends ObjectView {
    private static final int BORDER = 4;
    private static final int PADDING = 4;
    private static final int BUTTON_PADDING = 2;

    private final @Mandatory @Link Object entity;
    private final @Mandatory @Part List<RefreshableView> selects = new List<>();
    private final @Mandatory @Shared UiPanel panel = new UiPanel(BORDER, PADDING, TOP);

    private final @Mandatory @Shared EntityClassRepository entityClassRepository = EntityClasses.getRepository();

    public EntityView(@Mandatory MainWindow mainWindow, @Mandatory Object entity) {
        this.entity = entity;

        EntityClass entityClass = entityClassRepository.get(entity.getClass());
        int y = 0;
        for(EntityField entityField : entityClass.getFields()){
            if(isList(entityField)){
                if(isLink(entityField)){
                    addMultiSelect(new EntityLinkMultiSelect(mainWindow, entity, entityField), y++);
                }

                if(isPart(entityField)){
                    addMultiSelect(new EntityPartMultiSelect(mainWindow, entity, entityField), y++);
                }

                if(isValue(entityField)){
                    // todo
                }
            } else {
                if(isLink(entityField)){
                    addSingleSelect(new EntityLinkSingleSelect(mainWindow, entity, entityField), y++);
                }

                if(isPart(entityField)){
                    addSingleSelect(new EntityPartSingleSelect(mainWindow, entity, entityField), y++);
                }

                if(isValue(entityField)){
                    if(is(entityField, String.class)){
                        addSingleSelect(new EntityStringValueSingleSelect(mainWindow, entity, entityField), y++);
                    }

                    if(is(entityField, Integer.class)){
                        addSingleSelect(new EntityIntegerValueSingleSelect(mainWindow, entity, entityField), y++);
                    }

                    if(is(entityField, Boolean.class)){
                        addSingleSelect(new EntityBooleanValueSingleSelect(mainWindow, entity, entityField), y++);
                    }

                    if(isEnum(entityField)){
                        addSingleSelect(new EntityEnumValueSingleSelect(mainWindow, entity, entityField), y++);
                    }
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

    private void addSingleSelect(@Mandatory EntitySingleSelect select, int y){
        panel.add(select.getLabel(), 0, y, 0, 0, MIDDLE, BOTH);
        panel.add((Component) select.getContent(), 1, y, 1, 0, MIDDLE, BOTH);
        panel.add(wrapButtons(select.getButtons()), 2, y, 0, 0, MIDDLE, BOTH);
        selects.addLast(select);
    }

    private void addMultiSelect(@Mandatory EntityMultiSelect select, int y){
        UiHorizontalPanel horizontalPanel = new UiHorizontalPanel(0, PADDING, LEFT);
        horizontalPanel.add(select.getLabel(), 0, 0, 1, 0, LEFT, HORIZONTAL);
        horizontalPanel.add(wrapButtons(select.getButtons()), 1, 0, 0, 0, RIGHT, NONE);
        horizontalPanel.rebuild();

        UiVerticalPanel verticalPanel = new UiVerticalPanel(0, PADDING, TOP);
        verticalPanel.add(horizontalPanel, 0, 0, 1, 0, LEFT, HORIZONTAL);
        verticalPanel.add(select.getContent(), 0, 1, 1, 1, MIDDLE, BOTH);
        verticalPanel.rebuild();

        panel.add(verticalPanel, 0, y, 1, 1, MIDDLE, BOTH, 3, 1);
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
        for(RefreshableView field : selects){
            field.refresh();
        }
    }

    @Override
    public Object getObject() {
        return entity;
    }
}
