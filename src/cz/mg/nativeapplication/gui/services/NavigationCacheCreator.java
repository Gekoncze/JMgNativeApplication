package cz.mg.nativeapplication.gui.services;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.collections.map.Map;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClasses;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.mg.entities.MgProject;
import cz.mg.nativeapplication.mg.entities.components.MgComponent;
import cz.mg.nativeapplication.gui.other.NavigationCache;

import java.util.Arrays;
import java.util.Comparator;

import static cz.mg.nativeapplication.gui.other.NavigationCache.Node;


public @Service class NavigationCacheCreator {
    public @Mandatory NavigationCache create(@Optional MgProject project) {
        Map<Object, Node> map = new Map<>();
        return new NavigationCache(
            map, createNode(map, null, null, project)
        );
    }

    private @Optional Node createNode(
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

        Node node = new Node(parent, self, getLabel(parentField, self));
        map.set(self, node);

        if(self.getClass().isAnnotationPresent(Entity.class)){
            EntityClass entityClass = EntityClasses.getRepository().get(self.getClass());
            for(EntityField entityField : entityClass.getFields()){
                if(entityField.isAnnotationPresent(Part.class)){
                    Node childNode = createNode(map, node, entityField, entityField.get(self));
                    if(childNode != null){
                        node.getChildren().addLast(childNode);
                    }
                }
            }
        } else if(self instanceof Iterable){
            for(Object part : (Iterable) self){
                Node childNode = createNode(map, node, null, part);
                if(childNode != null){
                    node.getChildren().addLast(childNode);
                }
            }
        }

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

        sort(node.getChildren());

        return node;
    }

    private @Mandatory String getLabel(@Optional EntityField parentField, @Optional Object self){
        String objectName = new ObjectNameProvider().get(self);
        String parentFieldName = parentField != null ? parentField.getName() : null;
        return parentFieldName != null ? parentFieldName : objectName;
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
                Comparator.comparing(
                    node -> ((Node) node).getLabel().toLowerCase()
                )
            );
            list.clear();
            list.addCollectionLast(array);
        }
    }
}
