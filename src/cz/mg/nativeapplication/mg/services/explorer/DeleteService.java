package cz.mg.nativeapplication.mg.services.explorer;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClasses;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.mg.entities.MgProject;
import cz.mg.nativeapplication.mg.services.history.Action;
import cz.mg.nativeapplication.mg.services.history.CompositeAction;
import cz.mg.nativeapplication.mg.services.history.RemoveListItemAction;
import cz.mg.nativeapplication.mg.services.history.SetEntityFieldAction;


public @Service class DeleteService {
    private final @Mandatory @Shared SearchService searchService = new SearchService();

    public @Mandatory CompositeAction remove(
        @Mandatory MgProject project,
        @Mandatory Object parent,
        @Mandatory EntityField targetField
    ){
        int index = 0;
        EntityClass entityClass = EntityClasses.getRepository().get(parent.getClass());
        for(EntityField entityField : entityClass.getFields()){
            if(entityField == targetField){
                return remove(project, parent, index);
            }
            index++;
        }

        throw new IllegalArgumentException(
            "Could not find field '" + targetField.getName() + "' in class '" + entityClass.getName() + "'."
        );
    }

    public @Mandatory CompositeAction remove(
        @Mandatory MgProject project,
        @Mandatory Object parent,
        int index
    ){
        List<Action> actions = new List<>();
        Object target = readFromParent(parent, index);

        actions.addLast(
            removeFromParent(parent, index)
        );

        if(!hasOwner(project, target)){
            actions.addLast(
                delete(project, target)
            );
        }

        return new CompositeAction(actions);
    }

    private @Mandatory Action removeFromParent(@Optional Object parent, int index){
        if(parent == null){
            throw new UnsupportedOperationException("Cannot delete root object.");
        }

        Action action = removeFromParentAction(parent, index);
        action.redo();
        return action;
    }

    private @Mandatory Action removeFromParentAction(@Mandatory Object parent, int index){
        if(parent instanceof List){
            return removeFromListAction(parent, index);
        } else if(parent.getClass().isAnnotationPresent(Entity.class)) {
            return removeFromEntityAction(parent, index);
        } else {
            throw new UnsupportedOperationException("Unsupported parent object type for delete: '" + parent.getClass().getSimpleName() + "'.");
        }
    }

    private @Mandatory Action removeFromListAction(@Mandatory Object parent, int index){
        List list = (List) parent;
        Object target = list.get(index);
        return new RemoveListItemAction(list, index, target);
    }

    private @Mandatory Action removeFromEntityAction(@Mandatory Object parent, int index){
        EntityClass entityClass = EntityClasses.getRepository().get(parent.getClass());
        EntityField entityField = entityClass.getFields().get(index);
        Object target = entityField.get(parent);
        return new SetEntityFieldAction(parent, entityField, target, null);
    }

    private @Optional Object readFromParent(@Mandatory Object parent, int index){
        if(parent instanceof List){
            return readFromListAction(parent, index);
        } else if(parent.getClass().isAnnotationPresent(Entity.class)) {
            return readFromEntityAction(parent, index);
        } else {
            throw new UnsupportedOperationException("Unsupported parent object type for delete: '" + parent.getClass().getSimpleName() + "'.");
        }
    }

    private @Mandatory Object readFromListAction(@Mandatory Object parent, int index){
        List list = (List) parent;
        return list.get(index);
    }

    private @Mandatory Object readFromEntityAction(@Mandatory Object parent, int index){
        EntityClass entityClass = EntityClasses.getRepository().get(parent.getClass());
        EntityField entityField = entityClass.getFields().get(index);
        return entityField.get(parent);
    }

    public boolean hasOwner(@Mandatory MgProject project, @Mandatory Object target){
        List<SearchResult> usages = searchService.search(project, target);
        for(SearchResult usage : usages){
            if(usage.getResult().isPart()){
                return true;
            }
        }
        return false;
    }

    private @Mandatory CompositeAction delete(@Mandatory MgProject project, @Mandatory Object target){
        List<Action> actions = new List<>();

        List<SearchResult> usages = searchService.search(project, target);
        for(SearchResult usage : usages){
            Node parentNode = usage.getResult().getParent();
            Object parent = parentNode != null ? parentNode.getObject() : null;
            actions.addLast(
                removeFromParent(parent, usage.getIndex())
            );
        }

        actions.addLast(
            deleteChildren(project, target)
        );

        return new CompositeAction(actions);
    }

    private @Mandatory CompositeAction deleteChildren(@Mandatory MgProject project, @Mandatory Object target){
        List<Action> actions = new List<>();
        Node targetNode = Node.create(null, target, true);
        for(int i = 0; i < targetNode.getNodes().count(); i++){
            actions.addLast(
                remove(project, target, i)
            );
        }
        return new CompositeAction(actions);
    }
}
