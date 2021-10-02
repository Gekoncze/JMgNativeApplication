package cz.mg.nativeapplication.mg.services.explorer;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.list.List;
import cz.mg.collections.array.ReadableArray;


public @Utility interface Node {
    public @Optional Node getParent();
    public @Optional Object getObject();
    public boolean isPart();
    public int count();
    public @Mandatory ReadableArray<Node> getNodes();

    public static @Mandatory Node create(@Optional Node parent, @Optional Object object, boolean part){
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
