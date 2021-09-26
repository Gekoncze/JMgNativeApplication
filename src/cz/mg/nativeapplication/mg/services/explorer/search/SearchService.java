package cz.mg.nativeapplication.mg.services.explorer.search;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;
import cz.mg.collections.set.Set;
import cz.mg.nativeapplication.mg.entities.MgProject;
import cz.mg.nativeapplication.mg.services.explorer.Node;


public @Service class SearchService {
    public @Mandatory List<SearchResult> search(@Mandatory MgProject project, @Mandatory Object target){
        List<SearchResult> results = new List<>();
        search(Node.create(null, project, true), target, results, new Set<>());
        return results;
    }

    private void search(
        @Mandatory Node parentNode,
        @Mandatory Object target,
        @Mandatory List<SearchResult> results,
        @Mandatory Set<Object> searched
    ){
        searched.set(target);

        int i = 0;
        for(Node childNode : parentNode.getNodes()){
            Object child = childNode.getObject();
            if(child != null){
                if(child == target){
                    results.addLast(new SearchResult(childNode, i));
                }

                if(!searched.contains(child)){
                    search(childNode, target, results, searched);
                }
            }
            i++;
        }
    }
}
