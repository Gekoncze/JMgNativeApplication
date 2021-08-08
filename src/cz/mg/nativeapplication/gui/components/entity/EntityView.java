package cz.mg.nativeapplication.gui.components.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Shared;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.gui.components.MainWindow;
import cz.mg.nativeapplication.gui.components.controls.UiButton;
import cz.mg.nativeapplication.gui.components.controls.UiHorizontalPanel;
import cz.mg.nativeapplication.gui.components.entity.multi.EntityMultiSelect;
import cz.mg.nativeapplication.gui.components.entity.single.EntitySingleSelect;
import cz.mg.nativeapplication.gui.components.entity.single.link.EntityFieldLinkSelect;
import cz.mg.nativeapplication.gui.components.entity.single.part.EntityFieldPartSelect;
import cz.mg.nativeapplication.gui.components.entity.single.value.EntityIntegerFieldValueSelect;
import cz.mg.nativeapplication.gui.components.entity.single.value.EntityStringFieldValueSelect;
import cz.mg.nativeapplication.gui.components.other.ObjectView;
import cz.mg.nativeapplication.gui.components.other.RefreshableView;
import cz.mg.nativeapplication.gui.components.controls.UiPanel;
import cz.mg.nativeapplication.sevices.EntityClass;
import cz.mg.nativeapplication.sevices.EntityClassCache;
import cz.mg.nativeapplication.sevices.EntityField;

import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Alignment.*;
import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Fill.BOTH;


public @Utility class EntityView extends ObjectView {
    private static final int BORDER = 4;
    private static final int PADDING = 4;
    private static final int BUTTON_PADDING = 2;

    private final @Mandatory @Link Object entity;
    private final @Mandatory @Part List<RefreshableView> selects = new List<>();
    private final @Mandatory @Shared UiPanel panel = new UiPanel(BORDER, PADDING, TOP);

    public EntityView(@Mandatory MainWindow mainWindow, @Mandatory Object entity) {
        this.entity = entity;

        EntityClass entityClass = EntityClassCache.getInstance().get(entity.getClass());
        int y = 0;
        for(EntityField entityField : entityClass.getFields()){
            if(!isList(entityField)){
                if(isLink(entityField)){
                    addSingleSelect(new EntityFieldLinkSelect(mainWindow, entity, entityField), y++);
                }

                if(isPart(entityField)){
                    addSingleSelect(new EntityFieldPartSelect(mainWindow, entity, entityField), y++);
                }

                if(isValue(entityField)){
                    if(is(entityField, String.class)){
                        addSingleSelect(new EntityStringFieldValueSelect(mainWindow, entity, entityField), y++);
                    }

                    if(is(entityField, Integer.class)){
                        addSingleSelect(new EntityIntegerFieldValueSelect(mainWindow, entity, entityField), y++);
                    }

                    // todo
                }
            } else {
                if(isLink(entityField)){
                    // todo
                }

                if(isPart(entityField)){
                    // todo
                }

                if(isValue(entityField)){
                    // todo
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
        return List.class.isAssignableFrom(entityField.getField().getType());
    }

    private void addSingleSelect(@Mandatory EntitySingleSelect select, int y){
        panel.add(select.getLabel(), 0, y, 0, 0, MIDDLE, BOTH);
        panel.add(select.getContent(), 1, y, 1, 0, MIDDLE, BOTH);
        panel.add(wrapButtons(select.getButtons()), 2, y, 0, 0, MIDDLE, BOTH);
        selects.addLast(select);
    }

    private void addMultiSelect(@Mandatory EntityMultiSelect select, int y){
        // todo - first row label + buttons, second row list view
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
