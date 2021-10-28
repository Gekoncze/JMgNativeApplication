package cz.mg.nativeapplication.mg.services.explorer;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClasses;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.mg.entities.MgProject;


public @Service class DeleteService {
    private final @Mandatory @Shared SearchService searchService = new SearchService();
    private final @Mandatory @Shared ReadService readService = new ReadService();
    private final @Mandatory @Shared UpdateService updateService = new UpdateService();

    public void remove(@Mandatory MgProject project, @Mandatory Object parent, @Mandatory EntityField targetField){
        int i = 0;
        EntityClass entityClass = EntityClasses.getRepository().get(parent.getClass());
        for(EntityField entityField : entityClass.getFields()){
            if(entityField == targetField){
                remove(project, parent, i);
                return;
            }
            i++;
        }

        throw new IllegalArgumentException(
            "Could not find field '" + targetField.getName() + "' in class '" + entityClass.getName() + "'."
        );
    }

    public void remove(@Mandatory MgProject project, @Mandatory Object parent, int index){
        Object target = readService.read(parent, index);

        removeFromParent(parent, index);

        if(!hasOwner(project, target)){
            delete(project, target);
        }
    }

    private void removeFromParent(@Optional Object parent, int i){
        if(parent == null){
            throw new UnsupportedOperationException("Cannot delete root object.");
        }

        updateService.update(parent, i, null);
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

    private void delete(@Mandatory MgProject project, @Mandatory Object target){
        List<SearchResult> usages = searchService.search(project, target);
        for(SearchResult usage : usages){
            Node parentNode = usage.getResult().getParent();
            Object parent = parentNode != null ? parentNode.getObject() : null;
            removeFromParent(parent, usage.getIndex());
        }

        deleteChildren(project, target);
    }

    private void deleteChildren(@Mandatory MgProject project, @Mandatory Object target){
        Node targetNode = Node.create(null, target, true);
        for(int i = 0; i < targetNode.getNodes().count(); i++){
            remove(project, target, i);
        }
    }
}
