package cz.mg.nativeapplication.mg.services.explorer;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.list.List;


public @Utility class ListNode implements Node {
    private final @Optional @Link Node parent;
    private final @Mandatory @Link List object;
    private final @Value boolean part;

    public ListNode(@Optional Node parent, @Mandatory List object, boolean part) {
        this.parent = parent;
        this.object = object;
        this.part = part;
    }

    @Override
    public @Optional Node getParent() {
        return parent;
    }

    @Override
    public @Optional Object getObject() {
        return object;
    }

    @Override
    public boolean isPart() {
        return part;
    }

    @Override
    public int count() {
        return object.count();
    }

    @Override
    public @Mandatory ReadableArray<Node> getNodes() {
        Array<Node> nodes = new Array<>();
        int i = 0;
        for(Object item : object){
            nodes.set(Node.create(this, item, part), i);
            i++;
        }
        return nodes;
    }
}
