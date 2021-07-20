package cz.mg.nativeapplication.sevices;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.map.Map;


public class EntityClassCache {
    private static @Optional @Part EntityClassCache instance;

    public static @Mandatory EntityClassCache getInstance() {
        if(instance == null){
            instance = new EntityClassCache();
        }

        return instance;
    }

    private final @Mandatory @Part Map<@Link Class, EntityClass> cache = new Map<>();

    public EntityClassCache() {
    }

    public @Mandatory EntityClass get(@Mandatory Class clazz){
        EntityClass entityClass = cache.get(clazz);
        if(entityClass == null){
            entityClass = EntityClass.create(clazz);
            cache.set(clazz, entityClass);
        }
        return entityClass;
    }
}
