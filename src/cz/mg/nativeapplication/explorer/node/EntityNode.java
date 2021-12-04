package cz.mg.nativeapplication.explorer.node;

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
import cz.mg.entity.EntityClassProvider;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.explorer.services.NodeFactory;


public @Utility class EntityNode implements Node {
    private final @Mandatory @Shared EntityClassProvider entityClassProvider = new EntityClassProvider();
    private final @Mandatory @Shared NodeFactory nodeFactory = new NodeFactory();

    private final @Optional @Link Node parent;
    private final @Mandatory @Link Object object;
    private final @Value boolean part;

    public EntityNode(@Optional Node parent, @Mandatory Object object, boolean part) {
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
        return entityClassProvider
            .get(object.getClass())
            .getFields()
            .count();
    }

    @Override
    public @Mandatory ReadableArray<Node> getNodes() {
        EntityClass entityClass = entityClassProvider.get(object.getClass());
        Array<Node> nodes = new Array<>(entityClass.getFields().count());
        int i = 0;
        for(EntityField field : entityClass.getFields()){
            boolean isPart = field.isAnnotationPresent(Part.class) || field.isAnnotationPresent(Shared.class);
            nodes.set(nodeFactory.create(this, field.get(object), isPart), i);
            i++;
        }
        return nodes;
    }
}
