package cz.mg.nativeapplication.gui.components;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.nativeapplication.sevices.EntityClass;
import cz.mg.nativeapplication.sevices.EntityClassCache;
import cz.mg.nativeapplication.sevices.EntityField;

import javax.swing.*;


public class EntityView extends JPanel {
    private final @Mandatory Object entity;

    public EntityView(@Mandatory Object entity) {
        this.entity = entity;
        EntityClass<?> entityClass = EntityClassCache.getInstance().get(entity.getClass());
        for(EntityField entityField : entityClass.getFields()){
            boolean isCollection = Iterable.class.isAssignableFrom(entityField.getType());
            boolean isEntity = entityField.getType().isAnnotationPresent(Entity.class);

            boolean isValue = entityField.isAnnotationPresent(Value.class);
            boolean isPart = entityField.isAnnotationPresent(Part.class);
            boolean isLink = entityField.isAnnotationPresent(Link.class);

            // todo
        }
    }
}
