package cz.mg.nativeapplication.mg.services.explorer;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;


public @Utility class NullNode implements Node {
    private final @Optional @Link Node parent;

    public NullNode(@Optional Node parent) {
        this.parent = parent;
    }

    @Override
    public @Optional Node getParent() {
        return parent;
    }

    @Override
    public @Optional Object getObject() {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public @Mandatory ReadableArray<Node> getNodes() {
        return new Array<>(0);
    }

    @Override
    public boolean isPart() {
        return false;
    }
}
