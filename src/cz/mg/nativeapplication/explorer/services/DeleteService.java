package cz.mg.nativeapplication.explorer.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.explorer.utilities.Node;
import cz.mg.nativeapplication.explorer.utilities.SearchResult;


public @Service class DeleteService {
    private final @Mandatory @Shared SearchService searchService = new SearchService();
    private final @Mandatory @Shared ReadService readService = new ReadService();
    private final @Mandatory @Shared UpdateService updateService = new UpdateService();
    private final @Mandatory @Shared OwnershipService ownershipService = new OwnershipService();

    public void remove(@Mandatory Object root, @Mandatory Object parent, int index){
        Object target = readService.read(parent, index);

        if(target != null){
            removeFromParent(parent, index);

            if(!hasOwner(root, target)){
                delete(root, target);
            }
        }
    }

    private void removeFromParent(@Optional Object parent, int i){
        if(parent == null){
            throw new UnsupportedOperationException("Cannot delete root object.");
        }

        updateService.update(parent, i, null);
    }

    public boolean hasOwner(@Mandatory Object root, @Mandatory Object target){
        List<SearchResult> usages = searchService.search(root, target);
        for(SearchResult usage : usages){
            if(ownershipService.isOwnedByParent(usage.getResult())){
                return true;
            }
        }
        return false;
    }

    private void delete(@Mandatory Object root, @Mandatory Object target){
        List<SearchResult> usages = searchService.search(root, target);
        for(SearchResult usage : usages){
            Node parentNode = usage.getResult().getParentNode();
            Object parent = parentNode != null ? parentNode.getObject() : null;
            removeFromParent(parent, usage.getIndex());
        }

        deleteChildren(root, target);
    }

    private void deleteChildren(@Mandatory Object root, @Mandatory Object target){
        for(int i = 0; i < readService.count(target); i++){
            remove(root, target, i);
        }
    }
}
