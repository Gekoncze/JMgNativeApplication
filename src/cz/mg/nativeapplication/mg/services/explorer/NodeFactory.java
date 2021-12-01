package cz.mg.nativeapplication.mg.services.explorer;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.mg.services.explorer.node.*;


public @Service class NodeFactory {
    public @Mandatory Node create(@Optional Node parent, @Optional Object object, boolean part){
        if(object == null){
            return new NullNode(parent);
        }

        if(object instanceof List){
            return new ListNode(parent, (List) object, part);
        }

        if(object.getClass().isAnnotationPresent(Entity.class)){
            return new EntityNode(parent, object, part);
        }

        return new LeafNode(parent, object, part);
    }
}
