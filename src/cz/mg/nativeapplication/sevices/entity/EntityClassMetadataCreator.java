package cz.mg.nativeapplication.sevices.entity;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.ArrayList;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ListSorter;
import cz.mg.collections.map.Map;

import java.lang.reflect.Modifier;
import java.util.Comparator;


public @Service class EntityClassMetadataCreator {
    public @Mandatory EntityClassMetadata create(){
        Map<Class, EntityClass> map = new Map<>();
        Map<Class, List<EntityClass>> subclassMap = new Map<>();

        List<Class> classes = new EntityClassProvider().get();
        for(Class clazz : classes){
            if(isInstantiable(clazz)){
                resolve(clazz, map, subclassMap);
            }
        }

        for(EntityClass entityClass : map.iterator()){
            ListSorter.sortInPlace(
                subclassMap.get(entityClass.getClazz()),
                Comparator.comparing(EntityClass::getName)
            );
        }

        return new EntityClassMetadata(map);
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

    private void resolve(
        @Mandatory Class clazz,
        @Mandatory Map<Class, EntityClass> map,
        @Mandatory Map<Class, List<EntityClass>> subclassMap
    ){
        EntityClass entityClass = getOrCreate(clazz, map, subclassMap);
        Class current = clazz;
        while(current != null){
            if(current != Object.class){
                getOrCreate(current, map, subclassMap);
                subclassMap.get(current).addLast(entityClass);
            }
            current = current.getSuperclass();
        }
    }

    private EntityClass getOrCreate(
        @Mandatory Class clazz,
        @Mandatory Map<Class, EntityClass> map,
        @Mandatory Map<Class, List<EntityClass>> subclassMap
    ){
        EntityClass entityClass = map.get(clazz);
        if(entityClass == null){
            List<EntityClass> subclasses = new ArrayList<>();
            entityClass = new EntityClassCreator().create(clazz, subclasses);
            map.set(clazz, entityClass);
            subclassMap.set(clazz, subclasses);
        }
        return entityClass;
    }
}
