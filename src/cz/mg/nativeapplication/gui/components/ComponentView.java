//package cz.mg.nativeapplication.gui.components;
//
//import cz.mg.annotations.classes.Entity;
//import cz.mg.annotations.requirement.Mandatory;
//import cz.mg.annotations.storage.Link;
//import cz.mg.annotations.storage.Part;
//import cz.mg.annotations.storage.Value;
//import cz.mg.nativeapplication.entities.mg.components.MgComponent;
//import cz.mg.nativeapplication.sevices.EntityClass;
//import cz.mg.nativeapplication.sevices.EntityClassCache;
//import cz.mg.nativeapplication.sevices.EntityField;
//import cz.mg.nativeapplication.sevices.gui.CollectionTypeProvider;
//
//import javax.swing.*;
//
//
//public class ComponentView extends JPanel {
//    private final @Mandatory MgComponent component;
//
//    public ComponentView(@Mandatory MgComponent component) {
//        this.component = component;
//        EntityClass<?> entityClass = EntityClassCache.getInstance().get(component.getClass());
//        for(EntityField entityField : entityClass.getFields()){
//            boolean isEntity = entityField.getType().isAnnotationPresent(Entity.class);
//            boolean isCollection = Iterable.class.isAssignableFrom(entityField.getType());
//            Class collectionType = new CollectionTypeProvider().get(entityField.getField());
//
//            boolean isValue = entityField.isAnnotationPresent(Value.class);
//            boolean isPart = entityField.isAnnotationPresent(Part.class);
//            boolean isLink = entityField.isAnnotationPresent(Link.class);
//
//            // todo
//        }
//    }
//}
