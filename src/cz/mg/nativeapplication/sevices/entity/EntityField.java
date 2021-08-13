package cz.mg.nativeapplication.sevices.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;


public @Utility class EntityField {
    private final @Mandatory EntityClass entityClass;
    private final @Mandatory Field field;

    public EntityField(@Mandatory EntityClass entityClass, @Mandatory Field field) {
        this.entityClass = entityClass;
        this.field = field;
    }

    public @Mandatory EntityClass getEntityClass() {
        return entityClass;
    }

    public @Mandatory Field getField() {
        return field;
    }

    public @Mandatory String getName(){
        return field.getName();
    }

    public @Mandatory Class getType(){
        return field.getType();
    }

    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass){
        return field.isAnnotationPresent(annotationClass);
    }

    public @Optional Object get(@Mandatory Object parent){
        try {
            return field.get(parent);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public void set(@Mandatory Object parent, @Optional Object value){
        try {
            field.set(parent, value);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e){
            throw new RuntimeException(createSetFieldErrorMessage(value), e);
        }
    }

    private @Mandatory String createSetFieldErrorMessage(@Optional Object value){
        String type = value == null ? "null" : value.getClass().getSimpleName();
        return "Could not set field " + entityClass.getName() + "." + field.getName() + " of type " + field.getType().getSimpleName() + " to a value of type " + type + ".";
    }
}
