package cz.mg.nativeapplication.sevices.entity;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ListSorter;

import java.lang.reflect.Modifier;
import java.util.Comparator;


public @Service class EntityClassSubclassResolver {
    public void resolve(@Mandatory EntityClassMetadata metadata){
        List<Class> classes = new EntityClassProvider().get();
        for(Class clazz : classes){
            if(isInstantiable(clazz)){
                resolve(metadata, clazz);
            }
        }

        for(EntityClass entityClass : metadata.cache.iterator()){
            entityClass.subclasses = ListSorter.sort(
                entityClass.subclasses,
                Comparator.comparing(EntityClass::getName)
            );
        }
    }

    private void resolve(@Mandatory EntityClassMetadata metadata, @Mandatory Class clazz){
        EntityClass entityClass = metadata.get(clazz);
        Class current = clazz;
        while(current != null){
            if(current != Object.class){
                EntityClass parentEntityClass = metadata.get(current);
                parentEntityClass.subclasses.addLast(entityClass);
            }
            current = current.getSuperclass();
        }
    }

    private boolean isInstantiable(@Mandatory Class clazz){
        if(!Modifier.isAbstract(clazz.getModifiers())){
            try {
                clazz.getConstructor();
                return true;
            } catch (Exception e){
                return false;
            }
        }
        return false;
    }
}
