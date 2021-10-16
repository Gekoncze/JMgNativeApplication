package cz.mg.nativeapplication.mg.services.explorer;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClasses;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.mg.services.history.Action;
import cz.mg.nativeapplication.mg.services.history.SetEntityFieldAction;
import cz.mg.nativeapplication.mg.services.history.SetListItemAction;
import cz.mg.nativeapplication.mg.services.history.Transaction;


public @Service class UpdateService {
    public void update(@Mandatory Transaction transaction, @Mandatory Object parent, int i, @Optional Object value){
        transaction.run(
            createAction(parent, i, value)
        );
    }

    private static @Mandatory Action createAction(@Mandatory Object parent, int i, @Optional Object value){
        if(parent instanceof List){
            return createListAction(parent, i, value);
        } else if(parent.getClass().isAnnotationPresent(Entity.class)) {
            return createEntityAction(parent, i, value);
        } else {
            throw new UnsupportedOperationException("Unsupported parent object type for set action: '" + parent.getClass().getSimpleName() + "'.");
        }
    }

    private static @Mandatory Action createListAction(@Mandatory Object parent, int i, @Optional Object value){
        List list = (List) parent;
        Object target = list.get(i);
        return new SetListItemAction(list, i, target, value);
    }

    private static @Mandatory Action createEntityAction(@Mandatory Object parent, int i, @Optional Object value){
        EntityClass entityClass = EntityClasses.getRepository().get(parent.getClass());
        EntityField entityField = entityClass.getFields().get(i);
        Object target = entityField.get(parent);
        return new SetEntityFieldAction(parent, entityField, target, value);
    }
}
