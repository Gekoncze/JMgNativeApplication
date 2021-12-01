package cz.mg.nativeapplication.mg.services.explorer.node;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Shared;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.mg.services.explorer.NodeFactory;


public @Utility class ListNode implements Node {
    private final @Mandatory @Shared NodeFactory nodeFactory = new NodeFactory();

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
    public @Mandatory Object getObject() {
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
        Array<Node> nodes = new Array<>(object.count());
        int i = 0;
        for(Object item : object){
            nodes.set(nodeFactory.create(this, item, part), i);
            i++;
        }
        return nodes;
    }
}
