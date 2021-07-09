package cz.mg.nativeapplication.gui.utilities;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.collections.map.Map;


public @Utility class NavigationCache {
    private final @Mandatory @Link Map<Object, Node> map;
    private final @Optional @Part Node root;

    public NavigationCache(@Mandatory Map<Object, Node> map, @Optional Node root) {
        this.map = map;
        this.root = root;
    }

    public @Optional Node getRoot() {
        return root;
    }

    public @Optional Node get(@Optional Object entity){
        if(entity != null){
            return map.get(entity);
        } else {
            return null;
        }
    }

    public static class Node {
        private final @Optional @Link Node parent;
        private final @Mandatory @Link Object self;
        private final @Mandatory @Part List<Node> children = new List<>();
        private final @Mandatory @Part String name;

        public Node(@Optional Node parent, @Mandatory Object self, @Mandatory String name) {
            this.parent = parent;
            this.self = self;
            this.name = name;
        }

        public @Optional Node getParent() {
            return parent;
        }

        public @Mandatory Object getSelf() {
            return self;
        }

        public @Mandatory List<Node> getChildren() {
            return children;
        }

        public @Mandatory String getName() {
            return name;
        }
    }
}
