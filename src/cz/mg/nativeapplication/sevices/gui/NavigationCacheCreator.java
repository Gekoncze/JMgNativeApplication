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
import cz.mg.nativeapplication.entities.mg.existing.MgExisting;
import cz.mg.nativeapplication.gui.icons.IconGallery;
import cz.mg.nativeapplication.gui.utilities.NavigationCache;
import cz.mg.nativeapplication.sevices.EntityClass;
import cz.mg.nativeapplication.sevices.EntityClassCache;
import cz.mg.nativeapplication.sevices.EntityField;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;

import static cz.mg.nativeapplication.gui.utilities.NavigationCache.Node;


public @Service class NavigationCacheCreator {
    public @Mandatory NavigationCache create(@Optional MgProject project, @Optional IconGallery iconGallery) {
        Map<Object, Node> map = new Map<>();
        return new NavigationCache(
            map, createNode(iconGallery, map, null, null, project)
        );
    }

    private @Optional Node createNode(
        @Mandatory IconGallery iconGallery,
        @Mandatory Map<Object, Node> map,
        @Optional Node parent,
        @Optional Field parentField,
        @Mandatory Object self
    ){
        if(self == null){
            return null;
        }

        checkCircularOwnership(parent, self);

        if(self.getClass().isAnnotationPresent(Entity.class)){
            if(self instanceof MgComponent || self instanceof MgProject){
                return createEntityNode(iconGallery, map, parent, parentField, self);
            }
        }

        if(self instanceof Iterable){
            if(parentField != null){
                Class clazz = new CollectionTypeProvider().get(parentField);
                if(clazz != null){
                    if(MgComponent.class.isAssignableFrom(clazz) || MgProject.class.isAssignableFrom(clazz)){
                        return createCollectionNode(iconGallery, map, parent, parentField, self);
                    }
                }
            }
        }

        return null;
    }

    private @Mandatory Node createEntityNode(
        @Mandatory IconGallery iconGallery,
        @Mandatory Map<Object, Node> map,
        @Optional Node parent,
        @Optional Field parentField,
        @Mandatory Object self
    ){
        EntityClass<?> entityClass = EntityClassCache.getInstance().get(self.getClass());
        Node node = new Node(
            parent,
            self,
            getEntityName(entityClass, self, parentField),
            getObjectIcon(iconGallery, self)
        );
        map.set(self, node);

        for(EntityField field : entityClass.getFields()){
            if(field.isAnnotationPresent(Part.class)){
                Object child = field.get(self);
                if(child != null){
                    Node childNode = createNode(iconGallery, map, node, field.getField(), child);
                    if(childNode != null){
                        node.getChildren().addLast(childNode);
                    }
                }
            }
        }

        sort(node.getChildren());
        return node;
    }

    private @Mandatory Node createCollectionNode(
        @Mandatory IconGallery iconGallery,
        @Mandatory Map<Object, Node> map,
        @Optional Node parent,
        @Optional Field parentField,
        @Mandatory Object self
    ){
        Iterable collection = (Iterable) self;
        Node node = new Node(
            parent,
            self,
            getObjectName(self, parentField),
            getObjectIcon(iconGallery, self)
        );
        map.set(self, parent);

        // Note: children inherit part annotation from their parent collection
        for(Object child : collection){
            if(child != null){
                Node childNode = createNode(iconGallery, map, node, null, child);
                if(childNode != null){
                    node.getChildren().addLast(childNode);
                }
            }
        }

        sort(node.getChildren());
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

    private @Mandatory String getEntityName(
        @Mandatory EntityClass<?> entityClass,
        @Mandatory Object entity,
        @Optional Field parentField
    ){
        String name = null;

        for(EntityField field : entityClass.getFields()){
            if(field.getType() == String.class){
                if(field.getName().equals("name")){
                    name = (String) field.get(entity);
                }
            }
        }

        if(name != null && name.trim().length() > 0){
            return name;
        } else {
            return getObjectName(entity, parentField);
        }
    }

    private @Mandatory String getObjectName(
        @Mandatory Object object,
        @Optional Field parentField
    ){
        if(parentField != null){
            return parentField.getName();
        } else {
            return object.getClass().getSimpleName();
        }
    }

    private @Optional Icon getObjectIcon(@Optional IconGallery iconGallery, @Mandatory Object object){
        if(iconGallery != null){
            String name = object instanceof MgExisting
                ? object.getClass().getSuperclass().getSimpleName().toLowerCase() + ".png"
                : object.getClass().getSimpleName().toLowerCase() + ".png";

            Icon icon = iconGallery.getIcon(name);
            if(icon == null){
                icon = iconGallery.getIcon(IconGallery.UNKNOWN);
            }
            return icon;
        } else {
            return null;
        }
    }
}
