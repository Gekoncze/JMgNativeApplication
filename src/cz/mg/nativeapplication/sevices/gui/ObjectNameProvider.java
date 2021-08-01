package cz.mg.nativeapplication.sevices.gui;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.entities.mg.components.MgComponent;
import cz.mg.nativeapplication.sevices.EntityClass;
import cz.mg.nativeapplication.sevices.EntityClassCache;
import cz.mg.nativeapplication.sevices.EntityField;


public @Service class ObjectNameProvider {
    public @Optional String getName(@Optional Object object, @Optional EntityField parentField){
        if(object == null){
            return null;
        }

        if(object.getClass().isAnnotationPresent(Entity.class)){
            EntityClass entityClass = EntityClassCache.getInstance().get(object.getClass());
            EntityField entityField = entityClass.getField("name");
            if(entityField != null){
                Object value = entityField.get(object);
                if(value instanceof String){
                    String name = (String) value;
                    if(name.trim().length() > 0){
                        return name;
                    }
                }
            }
        }

        if(parentField != null){
            return parentField.getName();
        }

        return object.getClass().getSimpleName();
    }

    public @Optional String getDisplayName(@Optional Object object){
        if(object != null){
            if(object instanceof MgComponent){
                MgComponent component = (MgComponent) object;
                if(component.name != null){
                    if(component.name.trim().length() > 0){
                        return component.name;
                    } else {
                        return object.getClass().getSimpleName();
                    }
                } else {
                    return object.getClass().getSimpleName();
                }
            } else {
                return object.getClass().getSimpleName();
            }
        } else {
            return null;
        }
    }
}
