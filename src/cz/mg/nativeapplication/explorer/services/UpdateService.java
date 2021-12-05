package cz.mg.nativeapplication.explorer.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.explorer.history.ActionFactory;
import cz.mg.nativeapplication.explorer.history.TransactionManagerProvider;
import cz.mg.nativeapplication.explorer.utilities.Node;
import cz.mg.nativeapplication.explorer.utilities.SearchResult;


public @Service class UpdateService {
    private final @Mandatory @Shared TransactionManagerProvider transactionManagerProvider = new TransactionManagerProvider();
    private final @Mandatory @Shared ActionFactory actionFactory = new ActionFactory();

    private final @Mandatory @Shared SearchService searchService = new SearchService();
    private final @Mandatory @Shared ReadService readService = new ReadService();
    private final @Mandatory @Shared OwnershipService ownershipService = new OwnershipService();

    public void update(@Mandatory Object root, @Optional Object object, int index, @Optional Object value){
        if(object != null){
            Object target = readService.read(object, index);
            if(target != value){
                set(object, index, value);

                if(target != null){
                    if(!ownershipService.hasOwner(root, target)){
                        delete(root, target);
                    }
                }
            }
        }
    }

    private void delete(@Mandatory Object root, @Mandatory Object target){
        List<SearchResult> usages = searchService.search(root, target);
        for(SearchResult usage : usages){
            Node parentNode = usage.getResult().getParentNode();
            Object parent = parentNode != null ? parentNode.getObject() : null;
            if(parent != null){
                set(parent, usage.getIndex(), null);
            } else {
                throw new UnsupportedOperationException("Cannot delete root object.");
            }
        }

        removeChildren(root, target);
    }

    private void removeChildren(@Mandatory Object root, @Mandatory Object target){
        for(int i = 0; i < readService.count(target); i++){
            update(root, target, i, null);
        }
    }

    public void set(@Mandatory Object object, int i, @Optional Object value){
        transactionManagerProvider.get().run(
            actionFactory.createSetAction(object, i, value)
        );
    }
}
