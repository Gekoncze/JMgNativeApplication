package cz.mg.nativeapplication.sevices.gui;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.collections.map.Map;
import cz.mg.nativeapplication.entities.mg.MgProject;
import cz.mg.nativeapplication.entities.mg.components.MgComponent;
import cz.mg.nativeapplication.gui.icons.IconGallery;
import cz.mg.nativeapplication.gui.other.NavigationCache;
import cz.mg.nativeapplication.sevices.EntityClass;
import cz.mg.nativeapplication.sevices.EntityClassCache;
import cz.mg.nativeapplication.sevices.EntityField;

import java.util.Arrays;
import java.util.Comparator;

import static cz.mg.nativeapplication.gui.other.NavigationCache.Node;


public @Service class NavigationCacheCreator {
    public @Mandatory NavigationCache create(@Optional MgProject project, @Mandatory IconGallery iconGallery) {
        Map<Object, Node> map = new Map<>();
        return new NavigationCache(
            map, createNode(iconGallery, map, null, null, project)
        );
    }

    private @Optional Node createNode(
        @Mandatory IconGallery iconGallery,
        @Mandatory Map<Object, Node> map,
        @Optional Node parent,
        @Optional EntityField parentField,
        @Optional Object self
    ){
        if(self == null){
            return null;
        }

        if(!(self instanceof MgProject || self instanceof MgComponent || self instanceof Iterable)){
            return null;
        }

        checkCircularOwnership(parent, self);

        Node node = new Node(parent, self,
            new ObjectNameProvider().getName(self, parentField),
            new ObjectIconProvider().getIcon(self, iconGallery)
        );
        map.set(self, node);

        if(self.getClass().isAnnotationPresent(Entity.class)){
            EntityClass entityClass = EntityClassCache.getInstance().get(self.getClass());
            for(EntityField entityField : entityClass.getFields()){
                if(entityField.isAnnotationPresent(Part.class)){
                    Node childNode = createNode(iconGallery, map, node, entityField, entityField.get(self));
                    if(childNode != null){
                        node.getChildren().addLast(childNode);
                    }
                }
            }
        } else if(self instanceof Iterable){
            for(Object part : (Iterable) self){
                Node childNode = createNode(iconGallery, map, node, null, part);
                if(childNode != null){
                    node.getChildren().addLast(childNode);
                }
            }
        }

        sort(node.getChildren());

        if(self.getClass().isAnnotationPresent(Entity.class)){
            // hide standalone lists
            if(node.getChildren().count() == 1){
                Node child = node.getChildren().getFirst();
                if(child.getSelf() instanceof Iterable){
                    node.getChildren().clear();
                    node.getChildren().addCollectionLast(child.getChildren());
                }
            }
        } else if(self instanceof Iterable){
            // hide empty lists
            if(node.getChildren().count() < 1){
                return null;
            }
        }

        return node;
    }

    private void checkCircularOwnership(@Mandatory Node parent, @Mandatory Object self){
        Node current = parent;
        while(current != null){
            if(current.getSelf() == self){
                throw new IllegalStateException("Circular ownership detected.");
            }
            current = current.getParent();
        }
    }

    private void sort(@Mandatory List<Node> list){
        if(!list.isEmpty()){
            Array<Node> array = new Array(list);
            Arrays.sort(
                array.getJavaArray(),
                Comparator.comparing(node -> ((Node) node).getName().toLowerCase())
            );
            list.clear();
            list.addCollectionLast(array);
        }
    }
}
