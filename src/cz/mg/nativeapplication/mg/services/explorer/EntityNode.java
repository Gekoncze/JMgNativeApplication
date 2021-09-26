package cz.mg.nativeapplication.mg.services.explorer;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Shared;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClasses;
import cz.mg.entity.EntityField;


public @Utility class EntityNode implements Node {
    private final @Optional @Link Node parent;
    private final @Mandatory @Link Object object;
    private final @Mandatory @Link EntityClass entityClass;
    private final @Value boolean part;

    public EntityNode(@Optional Node parent, @Mandatory Object object, boolean part) {
        this.parent = parent;
        this.object = object;
        this.entityClass = EntityClasses.getRepository().get(object.getClass());
        this.part = part;
    }

    @Override
    public @Optional Node getParent() {
        return parent;
    }

    @Override
    public Object getObject() {
        return object;
    }

    @Override
    public boolean isPart() {
        return part;
    }

    @Override
    public int count() {
        return entityClass.getFields().count();
    }

    @Override
    public @Mandatory ReadableArray<Node> getNodes() {
        Array<Node> nodes = new Array<>(entityClass.getFields().count());
        int i = 0;
        for(EntityField field : entityClass.getFields()){
            boolean isPart = field.isAnnotationPresent(Part.class) || field.isAnnotationPresent(Shared.class);
            nodes.set(Node.create(this, field.get(object), isPart), i);
            i++;
        }
        return nodes;
    }
}
