package cz.mg.nativeapplication.explorer.node;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.array.ReadableArray;


public @Utility interface Node {
    public @Optional Node getParent();
    public @Optional Object getObject();
    public boolean isPart();
    public int count();
    public @Mandatory ReadableArray<Node> getNodes();
}
