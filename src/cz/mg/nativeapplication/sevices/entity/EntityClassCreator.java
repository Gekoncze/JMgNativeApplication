package cz.mg.nativeapplication.sevices.entity;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.ArrayList;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ListSorter;

import java.lang.reflect.Field;
import java.util.Comparator;


public @Service class EntityClassCreator {
    public @Mandatory EntityClass create(@Mandatory Class clazz, @Mandatory List<EntityClass> subclasses){
        if(isEntity(clazz)){
            try {
                clazz.getConstructor();
            } catch (ReflectiveOperationException e){
                throw new IllegalArgumentException("Could not find constructor for class '" + clazz.getSimpleName() + "'.");
            }

            List<EntityField> fields = new ArrayList<>();
            EntityClass entityClass = new EntityClass(clazz, fields, subclasses);

            Class current = clazz;
            while(current != null){
                for(Field field : current.getDeclaredFields()){
                    fields.addLast(new EntityFieldCreator().create(entityClass, field));
                }
                current = current.getSuperclass();
            }

            ListSorter.sortInPlace(fields, Comparator.comparing(EntityField::getName));
            ListSorter.sortInPlace(subclasses, Comparator.comparing(EntityClass::getName));

            return entityClass;
        } else {
            throw new IllegalArgumentException("Missing entity annotation for class '" + clazz.getSimpleName() + "'.");
        }
    }

    private @Mandatory boolean isEntity(@Mandatory Class clazz){
        return clazz.isAnnotationPresent(Entity.class);
    }
}
