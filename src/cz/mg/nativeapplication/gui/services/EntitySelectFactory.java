package cz.mg.nativeapplication.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.components.entity.EntityLinkSelect;
import cz.mg.nativeapplication.gui.components.entity.EntityPartSelect;
import cz.mg.nativeapplication.gui.components.entity.EntitySelect;
import cz.mg.nativeapplication.gui.components.entity.value.EntityBooleanValueSelect;
import cz.mg.nativeapplication.gui.components.entity.value.EntityEnumValueSelect;
import cz.mg.nativeapplication.gui.components.entity.value.EntityIntegerValueSelect;
import cz.mg.nativeapplication.gui.components.entity.value.EntityStringValueSelect;


public @Service class EntitySelectFactory { // TODO
    public @Mandatory EntitySelect create(@Mandatory Object entity, @Mandatory EntityField entityField){
        if(isList(entityField)){
            return new EntityPartSelect(entity, entityField);
        }

        if(isLink(entityField)){
            return new EntityLinkSelect(entity, entityField);
        }

        if(isPart(entityField)){
            return new EntityPartSelect(entity, entityField);
        }

        if(isValue(entityField)){
            if(is(entityField, String.class)){
                return new EntityStringValueSelect(entity, entityField);
            }

            if(is(entityField, Integer.class)){
                return new EntityIntegerValueSelect(entity, entityField);
            }

            if(is(entityField, Boolean.class)){
                return new EntityBooleanValueSelect(entity, entityField);
            }

            if(isEnum(entityField)){
                return new EntityEnumValueSelect(entity, entityField);
            }
        }

        throw new UnsupportedOperationException(); // TODO
    }

    private boolean isLink(@Mandatory EntityField entityField){
        return entityField.isAnnotationPresent(Link.class);
    }

    private boolean isPart(@Mandatory EntityField entityField){
        return entityField.isAnnotationPresent(Part.class);
    }

    private boolean isValue(@Mandatory EntityField entityField){
        return entityField.isAnnotationPresent(Value.class);
    }

    private boolean is(@Mandatory EntityField entityField, @Mandatory Class clazz){
        return clazz.isAssignableFrom(entityField.getField().getType());
    }

    private boolean isList(@Mandatory EntityField entityField){
        return is(entityField, List.class);
    }

    private boolean isEnum(@Mandatory EntityField entityField){
        return is(entityField, Enum.class);
    }
}
