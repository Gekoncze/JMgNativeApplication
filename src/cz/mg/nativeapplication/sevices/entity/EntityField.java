package cz.mg.nativeapplication.sevices.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;


public @Utility class EntityField {
    private final @Mandatory Class clazz;
    private final @Mandatory Field field;

    public EntityField(@Mandatory Class clazz, @Mandatory Field field) {
        this.clazz = clazz;
        this.field = field;
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
        return "Could not set field " + clazz.getSimpleName() + "." + field.getName() + " of type " + field.getType().getSimpleName() + " to a value of type " + type + ".";
    }
}
