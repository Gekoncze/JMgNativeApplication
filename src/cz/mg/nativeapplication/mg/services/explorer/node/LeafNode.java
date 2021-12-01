package cz.mg.nativeapplication.mg.services.explorer.node;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;


public @Utility class LeafNode implements Node {
    private final @Optional @Link Node parent;
    private final @Mandatory @Link Object object;
    private final @Value boolean part;

    public LeafNode(@Optional Node parent, @Mandatory Object object, boolean part) {
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
        return 0;
    }

    @Override
    public @Mandatory ReadableArray<Node> getNodes() {
        return new Array<>();
    }
}
