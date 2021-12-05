package cz.mg.nativeapplication.explorer.utilities;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Shared;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.nativeapplication.explorer.Explorer;
import cz.mg.nativeapplication.explorer.services.ReadService;
import cz.mg.nativeapplication.explorer.services.UpdateService;


public @Utility class Node {
    private final @Mandatory @Shared ReadService readService = new ReadService();
    private final @Mandatory @Shared UpdateService updateService = new UpdateService();

    private final @Mandatory Explorer explorer;
    private final @Optional @Link Node parent;
    private final @Optional @Link Object object;
    private final @Value int index;

    public Node(@Mandatory Explorer explorer, @Optional Node parent, @Optional Object object, int index) {
        this.explorer = explorer;
        this.parent = parent;
        this.object = object;
        this.index = index;
    }

    public @Optional Node getParentNode() {
        return parent;
    }

    public @Optional Object getObject() {
        return object;
    }

    public int getIndex() {
        return index;
    }

    public int count() {
        return readService.count(object);
    }

    public @Mandatory ReadableList<Object> getChildObjects() {
        return readService.read(object);
    }

    public @Mandatory ReadableList<Node> getChildNodes(){
        List<Node> nodes = new List<>();
        int i = 0;
        for(Object child : getChildObjects()){
            nodes.addLast(new Node(explorer, this, child, i));
            i++;
        }
        return nodes;
    }

    public void set(@Optional Object value, int i){
        updateService.update(explorer, object, i, value);
    }
}
