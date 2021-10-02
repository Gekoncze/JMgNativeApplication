package cz.mg.nativeapplication.gui.components.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.EntityClass;
import cz.mg.nativeapplication.gui.components.controls.UiMenuItem;
import cz.mg.nativeapplication.gui.components.controls.UiPopupMenu;
import cz.mg.nativeapplication.gui.handlers.ActionUserEventHandler;
import cz.mg.nativeapplication.gui.services.ClassIconProvider;
import cz.mg.nativeapplication.mg.entities.existing.MgExisting;

import java.awt.*;


public @Utility class EntityClassPopupMenu extends UiPopupMenu {
    private final @Mandatory @Link EntityClass entityClass;
    private @Mandatory EntityClass selectedEntityClass;

    private final @Mandatory @Shared ClassIconProvider classIconProvider = new ClassIconProvider();

    public EntityClassPopupMenu(
        @Mandatory EntityClass entityClass,
        @Mandatory ActionUserEventHandler.Handler actionEventHandler
    ) {
        this.entityClass = entityClass;
        this.selectedEntityClass = entityClass;

        for(EntityClass option : entityClass.getSubclasses()){
            if(!MgExisting.class.isAssignableFrom(option.getClazz())){
                add(new UiMenuItem(
                    classIconProvider.get(option.getClazz()),
                    option.getName(),
                    () -> {
                        selectedEntityClass = option;
                        actionEventHandler.run();
                    }
                ));
            }
        }
    }

    public @Mandatory EntityClass getEntityClass() {
        return entityClass;
    }

    public @Mandatory EntityClass getSelectedEntityClass() {
        return selectedEntityClass;
    }

    public void show(@Mandatory Component component) {
        super.show(component, 0, component.getHeight());
    }

    public void select(@Mandatory Component component){
        if(entityClass.getSubclasses().count() == 1){
            getMenuItem(0).doClick();
        } else if(entityClass.getSubclasses().count() > 1) {
            show(component);
        }
    }
}
