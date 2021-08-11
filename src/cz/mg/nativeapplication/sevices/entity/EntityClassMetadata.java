package cz.mg.nativeapplication.sevices.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.map.Map;


public @Utility class EntityClassMetadata {
    private final @Mandatory @Part Map<@Link Class, EntityClass> map;

    public EntityClassMetadata(@Mandatory Map<Class, EntityClass> map) {
        this.map = map;
    }

    public @Mandatory EntityClass get(@Mandatory Class clazz){
        EntityClass entityClass = map.get(clazz);
        if(entityClass != null){
            return entityClass;
        } else {
            throw new IllegalArgumentException("Missing metadata for '" + clazz.getSimpleName() + "' class.");
        }
    }
}
