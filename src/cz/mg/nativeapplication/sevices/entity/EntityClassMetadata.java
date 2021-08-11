package cz.mg.nativeapplication.sevices.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.map.Map;


public @Utility class EntityClassMetadata {
    private static @Optional @Part EntityClassMetadata instance;

    static @Mandatory EntityClassMetadata getInstance() {
        if(instance == null){
            instance = new EntityClassMetadata();
            new EntityClassSubclassResolver().resolve(instance);
        }

        return instance;
    }

    final @Mandatory @Part Map<@Link Class, EntityClass> cache = new Map<>();

    private EntityClassMetadata() {
    }

    public @Mandatory EntityClass get(@Mandatory Class clazz){
        EntityClass entityClass = cache.get(clazz);
        if(entityClass == null){
            entityClass = new EntityClassCreator().create(clazz);
            cache.set(clazz, entityClass);
        }
        return entityClass;
    }
}
