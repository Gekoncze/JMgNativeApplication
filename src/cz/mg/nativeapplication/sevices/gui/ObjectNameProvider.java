package cz.mg.nativeapplication.sevices.gui;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.sevices.entity.EntityClass;
import cz.mg.nativeapplication.sevices.entity.EntityClassMetadataProvider;
import cz.mg.nativeapplication.sevices.entity.EntityField;


public @Service class ObjectNameProvider {
    public @Mandatory String get(@Optional Object object){
        if(object != null){
            if(object.getClass().isAnnotationPresent(Entity.class)){
                EntityClass entityClass = new EntityClassMetadataProvider().get(object.getClass());
                EntityField entityField = entityClass.getField("name");
                if(entityField != null){
                    Object value = entityField.get(object);
                    if(value instanceof String){
                        String name = (String) value;
                        if(name.trim().length() > 0){
                            return name;
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
                return object.getClass().getSimpleName();
            }
        } else {
            return "";
        }
    }
}
