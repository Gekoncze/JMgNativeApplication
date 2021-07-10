package cz.mg.nativeapplication.sevices.gui;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.entities.mg.components.MgComponent;
import cz.mg.nativeapplication.gui.utilities.NavigationCache;

import static cz.mg.nativeapplication.gui.utilities.NavigationCache.Node;


public @Service class ComponentSearch {
    private static final int SEARCH_RESULTS_LIMIT = 10;
    private static final int SEARCH_NAME_LIMIT = 1000;

    public @Mandatory List<MgComponent> search(
        @Mandatory NavigationCache navigationCache,
        @Mandatory Class typeFilter,
        @Mandatory String nameFilter
    ){
        List<MgComponent> results = new List<>();
        search(
            results,
            typeFilter,
            nameFilter,
            navigationCache.getRoot()
        );
        return results;
    }

    private void search(
        @Mandatory List<MgComponent> results,
        @Mandatory Class typeFilter,
        @Mandatory String nameFilter,
        @Optional Node node
    ) {
        if(results.count() < SEARCH_RESULTS_LIMIT){
            if(node != null){
                if(node.getSelf() instanceof MgComponent){
                    MgComponent component = (MgComponent) node.getSelf();
                    if(typeFilter.isAssignableFrom(component.getClass())){
                        if(matches(nameFilter, component.name)){
                            results.addLast(component);
                        }
                    }
                }

                for(Node child : node.getChildren()){
                    search(results, typeFilter, nameFilter, child);
                }
            }
        }
    }

    private boolean matches(@Mandatory String expectations, @Mandatory String reality){
        if(expectations.length() < 1){
            return true;
        }

        expectations = limit(expectations.toLowerCase(), SEARCH_NAME_LIMIT);
        reality = limit(reality.toLowerCase(), SEARCH_NAME_LIMIT);

        return reality.contains(expectations);
    }

    private @Mandatory String limit(@Mandatory String s, int limit){
        if(s.length() <= limit){
            return s;
        } else {
            return s.substring(0, limit);
        }
    }
}
