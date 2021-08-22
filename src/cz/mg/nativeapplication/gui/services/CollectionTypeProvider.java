package cz.mg.nativeapplication.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public @Service class CollectionTypeProvider {
    public @Optional Class get(@Mandatory Field field){
        if(Iterable.class.isAssignableFrom(field.getType())){
            if(field.getGenericType() instanceof ParameterizedType){
                ParameterizedType fieldType = (ParameterizedType) field.getGenericType();
                for(Type fieldParameterType : fieldType.getActualTypeArguments()){
                    if(fieldParameterType instanceof Class){
                        return (Class) fieldParameterType;
                    }
                }
            }
        }
        return null;
    }
}
