package cz.mg.nativeapplication.explorer.services;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClassProvider;
import cz.mg.entity.EntityField;


public @Service class ReadService {
    private final @Mandatory @Shared EntityClassProvider entityClassProvider = new EntityClassProvider();

    public @Optional Object read(@Mandatory Object parent, int i){
        if(parent instanceof List){
            return readFromList(parent, i);
        } else if(parent.getClass().isAnnotationPresent(Entity.class)) {
            return readFromEntity(parent, i);
        } else {
            throw new UnsupportedOperationException("Unsupported parent object type for read: '" + parent.getClass().getSimpleName() + "'.");
        }
    }

    private @Optional Object readFromList(@Mandatory Object parent, int i){
        List list = (List) parent;
        if(i < 0 || i >= list.count()) throw new ArrayIndexOutOfBoundsException(i + " out of " + list.count());
        return list.get(i);
    }

    private @Optional Object readFromEntity(@Mandatory Object parent, int i){
        EntityClass entityClass = entityClassProvider.get(parent.getClass());
        if(i < 0 || i >= entityClass.getFields().count()) throw new ArrayIndexOutOfBoundsException(i + " out of " + entityClass.getFields().count());
        EntityField entityField = entityClass.getFields().get(i);
        return entityField.get(parent);
    }
}
