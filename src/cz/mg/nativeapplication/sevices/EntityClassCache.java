package cz.mg.nativeapplication.sevices;

import cz.mg.collections.map.Map;


public class EntityClassCache {
    private static EntityClassCache instance;

    public static EntityClassCache getInstance() {
        if(instance == null){
            instance = new EntityClassCache();
        }

        return instance;
    }

    private final Map<Class, EntityClass> cache = new Map<>();

    public EntityClassCache() {
    }

    public EntityClass get(Class clazz){
        EntityClass entityClass = cache.get(clazz);
        if(entityClass == null){
            entityClass = EntityClass.create(clazz);
            cache.set(clazz, entityClass);
        }
        return entityClass;
    }
}
