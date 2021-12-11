package cz.mg.nativeapplication.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Shared;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.explorer.Explorer;
import cz.mg.nativeapplication.gui.components.fields.LinkField;
import cz.mg.nativeapplication.gui.components.fields.PartField;
import cz.mg.nativeapplication.gui.components.fields.ObjectField;
import cz.mg.nativeapplication.gui.components.fields.value.*;
import cz.mg.nativeapplication.gui.components.fields.value.BooleanValueField;
import cz.mg.nativeapplication.gui.components.fields.value.StringValueField;


public @Service class FieldFactory {
    private final @Mandatory @Shared EntityClassFieldService entityClassFieldService = new EntityClassFieldService();

    public @Mandatory ObjectField create(
        @Mandatory Explorer explorer,
        @Mandatory Object entity,
        @Mandatory EntityClass entityClass,
        @Mandatory EntityField entityField
    ){
        int index = entityClassFieldService.getIndexOf(entityClass, entityField);
        Class type = entityField.getType();
        String label = entityField.getName();

        if(isList(entityField)){
            throw new UnsupportedOperationException(); // TODO
        }

        if(isLink(entityField)){
            return new LinkField(explorer, entity, index, type, label);
        }

        if(isPart(entityField)){
            return new PartField(explorer, entity, index, type, label);
        }

        if(isValue(entityField)){
            if(is(entityField, String.class)){
                return new StringValueField(explorer, entity, index, type, label);
            }

            if(is(entityField, Integer.class)){
                return new IntegerValueField(explorer, entity, index, type, label);
            }

            if(is(entityField, Boolean.class)){
                return new BooleanValueField(explorer, entity, index, type, label);
            }

            if(isEnum(entityField)){
                return new EnumValueField(explorer, entity, index, type, label);
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
