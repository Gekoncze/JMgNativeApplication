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
import cz.mg.nativeapplication.explorer.history.*;
import cz.mg.nativeapplication.explorer.history.actions.SetEntityFieldAction;
import cz.mg.nativeapplication.explorer.history.actions.SetListItemAction;


public @Service class UpdateService {
    private final @Mandatory @Shared TransactionManagerProvider transactionManagerProvider = new TransactionManagerProvider();
    private final @Mandatory @Shared EntityClassProvider entityClassProvider = new EntityClassProvider();

    public void update(@Mandatory Object parent, int i, @Optional Object value){
        transactionManagerProvider.get().run(
            createAction(parent, i, value)
        );
    }

    private @Mandatory Action createAction(@Mandatory Object parent, int i, @Optional Object value){
        if(parent instanceof List){
            return createListAction(parent, i, value);
        } else if(parent.getClass().isAnnotationPresent(Entity.class)) {
            return createEntityAction(parent, i, value);
        } else {
            throw new UnsupportedOperationException("Unsupported parent object type for set action: '" + parent.getClass().getSimpleName() + "'.");
        }
    }

    private @Mandatory Action createListAction(@Mandatory Object parent, int i, @Optional Object value){
        List list = (List) parent;
        Object target = list.get(i);
        return new SetListItemAction(list, i, target, value);
    }

    private @Mandatory Action createEntityAction(@Mandatory Object parent, int i, @Optional Object value){
        EntityClass entityClass = entityClassProvider.get(parent.getClass());
        EntityField entityField = entityClass.getFields().get(i);
        Object target = entityField.get(parent);
        return new SetEntityFieldAction(parent, entityField, target, value);
    }
}
