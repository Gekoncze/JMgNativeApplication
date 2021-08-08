package cz.mg.nativeapplication.sevices.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;


public @Utility class EntityClass {
    private final @Mandatory Class clazz;
    private final @Mandatory Array<EntityField> fields;
    final @Mandatory List<EntityClass> subclasses = new List<>();

    EntityClass(@Mandatory Class clazz, @Mandatory Array<EntityField> fields) {
        this.clazz = clazz;
        this.fields = fields;
    }

    public @Mandatory Class getClazz() {
        return clazz;
    }

    public @Mandatory ReadableArray<EntityField> getFields() {
        return fields;
    }

    public @Mandatory ReadableList<EntityClass> getSubclasses() {
        return subclasses;
    }

    public @Optional EntityField getField(@Mandatory String name){
        for(EntityField field : fields){
            if(field.getName().equals(name)){
                return field;
            }
        }
        return null;
    }

    public @Mandatory Object newInstance(){
        try {
            return clazz.getConstructor().newInstance();
        } catch (ReflectiveOperationException e){
            throw new RuntimeException(e);
        }
    }

    public @Mandatory String getName(){
        return clazz.getSimpleName();
    }
}
