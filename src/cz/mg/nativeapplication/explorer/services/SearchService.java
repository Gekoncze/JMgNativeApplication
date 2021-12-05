package cz.mg.nativeapplication.explorer.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;
import cz.mg.collections.set.Set;
import cz.mg.nativeapplication.explorer.utilities.Node;
import cz.mg.nativeapplication.explorer.utilities.SearchResult;


public @Service class SearchService {
    public @Mandatory List<SearchResult> search(@Mandatory Object root, @Mandatory Object target){
        List<SearchResult> results = new List<>();
        search(new Node(null, root, -1), target, results, new Set<>());
        return results;
    }

    private void search(
        @Mandatory Node parentNode,
        @Mandatory Object target,
        @Mandatory List<SearchResult> results,
        @Mandatory Set<Object> searched
    ){
        Object parent = parentNode.getObject();

        if(!searched.contains(parent)){
            searched.set(parent);

            int i = 0;
            for(Node childNode : parentNode.getChildNodes()){
                Object child = childNode.getObject();
                if(child != null){
                    if(child == target){
                        results.addLast(new SearchResult(childNode, i));
                    }

                    search(childNode, target, results, searched);
                }
                i++;
            }
        }
    }
}
