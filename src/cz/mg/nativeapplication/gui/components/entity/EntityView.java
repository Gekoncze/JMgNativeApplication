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
import cz.mg.nativeapplication.explorer.Explorer;
import cz.mg.nativeapplication.gui.services.EntitySelectFactory;
import cz.mg.nativeapplication.gui.ui.UiHorizontalPanelBuilder;
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

    private final @Mandatory @Link Explorer explorer;
    private final @Mandatory @Link Object entity;
    private final @Mandatory @Part List<EntitySelect> selects = new List<>();
    private final @Mandatory @Shared UiPanel panel = new UiPanel(BORDER, PADDING, TOP);

    private final @Mandatory @Shared EntityClassProvider entityClassProvider = new EntityClassProvider();
    private final @Mandatory @Shared EntitySelectFactory entitySelectFactory = new EntitySelectFactory();

    public EntityView(@Mandatory Explorer explorer, @Mandatory Object entity) {
        this.explorer = explorer;
        this.entity = entity;

        EntityClass entityClass = entityClassProvider.get(entity.getClass());
        int y = 0;
        for(EntityField entityField : entityClass.getFields()){
            addSelect(entitySelectFactory.create(entity, entityField), y++);
        }

        panel.rebuild();
        setViewportView(panel);
    }

    private void addSelect(@Mandatory EntitySelect select, int y){
        panel.add(select.getLabel(), 0, y, 0, 0, MIDDLE, BOTH);
        panel.add(select.getContent().getField(), 1, y, 1, 0, MIDDLE, BOTH);
        panel.add(wrapButtons(select.getButtons()), 2, y, 0, 0, MIDDLE, BOTH);
        selects.addLast(select);
    }

    private @Mandatory UiHorizontalPanel wrapButtons(@Mandatory List<UiButton> buttons){
        return new UiHorizontalPanelBuilder()
            .setBorder(0)
            .setPadding(BUTTON_PADDING)
            .setContentAlignment(LEFT)
            .setWeightX(0)
            .setWeightY(0)
            .setComponentAlignment(MIDDLE)
            .setFill(BOTH)
            .addComponents(buttons)
            .build();
    }

    public @Mandatory Explorer getExplorer() {
        return explorer;
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
