package cz.mg.nativeapplication.gui.components.views;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClassProvider;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.explorer.Explorer;
import cz.mg.nativeapplication.gui.components.other.Refreshable;
import cz.mg.nativeapplication.gui.services.EntitySelectFactory;
import cz.mg.nativeapplication.gui.ui.controls.UiPanel;

import java.awt.*;
import java.awt.event.KeyEvent;


public @Utility class EntityView extends UiPanel implements ObjectView {
    private static final int BORDER = 4;
    private static final int PADDING = 4;

    private final @Mandatory @Shared EntityClassProvider entityClassProvider = new EntityClassProvider();
    private final @Mandatory @Shared EntitySelectFactory entitySelectFactory = new EntitySelectFactory();

    private final @Mandatory @Link Explorer explorer;
    private final @Mandatory @Link Object entity;

    public EntityView(@Mandatory Explorer explorer, @Mandatory Object entity) {
        super(BORDER, PADDING, UiPanel.Alignment.TOP);

        this.explorer = explorer;
        this.entity = entity;

        EntityClass entityClass = entityClassProvider.get(entity.getClass());
        for(EntityField entityField : entityClass.getFields()){
            addVertical(
                entitySelectFactory.create(entity, entityField),
                1, 0, Alignment.MIDDLE, Fill.BOTH
            );
        }
        rebuild();
    }

    public @Mandatory Explorer getExplorer() {
        return explorer;
    }

    @Override
    public void refresh() {
        for(Component component : getComponents()){
            if(component instanceof Refreshable){
                ((Refreshable) component).refresh();
            }
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
