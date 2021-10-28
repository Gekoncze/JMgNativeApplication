package cz.mg.nativeapplication.mg.services.explorer;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClasses;
import cz.mg.entity.EntityField;


public @Service class ReadService {
    public @Optional Object read(@Mandatory Object parent, int i){
        if(parent instanceof List){
            return readFromList(parent, i);
        } else if(parent.getClass().isAnnotationPresent(Entity.class)) {
            return readFromEntity(parent, i);
        } else {
            throw new UnsupportedOperationException("Unsupported parent object type for read: '" + parent.getClass().getSimpleName() + "'.");
        }
    }

    private @Mandatory Object readFromList(@Mandatory Object parent, int i){
        List list = (List) parent;
        if(i < 0 || i >= list.count()) throw new ArrayIndexOutOfBoundsException(i + " out of " + list.count());
        return list.get(i);
    }

    private @Mandatory Object readFromEntity(@Mandatory Object parent, int i){
        EntityClass entityClass = EntityClasses.getRepository().get(parent.getClass());
        if(i < 0 || i >= entityClass.getFields().count()) throw new ArrayIndexOutOfBoundsException(i + " out of " + entityClass.getFields().count());
        EntityField entityField = entityClass.getFields().get(i);
        return entityField.get(parent);
    }
}
