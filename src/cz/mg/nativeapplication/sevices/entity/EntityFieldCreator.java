package cz.mg.nativeapplication.sevices.entity;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


@Service class EntityFieldCreator {
    public @Mandatory EntityField create(@Mandatory Class clazz, @Mandatory Field field){
        if(isEntityField(field)){
            if(Modifier.isPublic(field.getModifiers())){
                return new EntityField(clazz, field);
            } else {
                throw new IllegalArgumentException("Entity field '" + clazz.getSimpleName() + "." + field.getName() + "' must be public.");
            }
        } else {
            throw new IllegalArgumentException("Missing entity field annotation for field '" + clazz.getSimpleName() + "." + field.getName() + "'.");
        }
    }

    private @Mandatory boolean isEntityField(@Mandatory Field field){
        return field.isAnnotationPresent(Value.class)
            || field.isAnnotationPresent(Part.class)
            || field.isAnnotationPresent(Link.class)
            || field.isAnnotationPresent(Shared.class)
            || field.isAnnotationPresent(Cache.class);
    }
}
