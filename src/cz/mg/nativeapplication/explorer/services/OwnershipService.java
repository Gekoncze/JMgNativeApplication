package cz.mg.nativeapplication.explorer.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.Entities;
import cz.mg.entity.EntityClassProvider;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.explorer.utilities.Node;
import cz.mg.nativeapplication.explorer.utilities.SearchResult;


public @Service class OwnershipService {
    private final @Mandatory @Shared EntityClassProvider entityClassProvider = new EntityClassProvider();
    private final @Mandatory @Shared SearchService searchService = new SearchService();

    public boolean hasOwner(@Mandatory Object root, @Mandatory Object target){
        return ownerCount(root, target) > 0;
    }

    public int ownerCount(@Mandatory Object root, @Mandatory Object target){
        int count = 0;
        List<SearchResult> usages = searchService.search(root, target);
        for(SearchResult usage : usages){
            if(isOwnedByParent(usage.getResult())){
                count++;
            }
        }
        return count;
    }

    private boolean isOwnedByParent(@Mandatory Node node){
        Node parent = node.getParentNode();
        if(parent != null){
            if(parent.getObject() instanceof List){
                // list items inherit ownership from parent
                return isOwnedByParent(parent);
            }

            if(Entities.isEntity(parent.getObject())){
                EntityField field = entityClassProvider
                    .get(parent.getObject().getClass())
                    .getFields()
                    .get(node.getIndex());

                return field.isAnnotationPresent(Part.class) || field.isAnnotationPresent(Shared.class);
            }

            throw new IllegalStateException("Leaf node cannot be a parent of another node.");
        } else {
            // root object is owned by default
            return true;
        }
    }
}
