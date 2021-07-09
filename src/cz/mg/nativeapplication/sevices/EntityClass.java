package cz.mg.nativeapplication.sevices;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.list.List;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;


public @Utility class EntityClass<T> {
    private final @Mandatory Class<T> clazz;
    private final @Mandatory Array<EntityField> fields;

    EntityClass(@Mandatory Class<T> clazz, @Mandatory Array<EntityField> fields) {
        this.clazz = clazz;
        this.fields = fields;
    }

    public @Mandatory Class<T> getClazz() {
        return clazz;
    }

    public @Mandatory ReadableArray<EntityField> getFields() {
        return fields;
    }

    public @Mandatory T newInstance(){
        try {
            return clazz.getConstructor().newInstance();
        } catch (ReflectiveOperationException e){
            throw new RuntimeException(e);
        }
    }

    public @Mandatory String getName(){
        return clazz.getSimpleName();
    }

    static <T> @Mandatory EntityClass<T> create(@Mandatory Class<T> clazz){
        if(isEntity(clazz)){
            try {
                clazz.getConstructor();
            } catch (ReflectiveOperationException e){
                throw new IllegalArgumentException("Could not find constructor for class '" + clazz.getSimpleName() + "'.");
            }

            List<EntityField> fields = new List<>();
            Class current = clazz;
            while(current != null){
                for(Field field : current.getDeclaredFields()){
                    fields.addLast(EntityField.create(clazz, field));
                }
                current = current.getSuperclass();
            }
            return new EntityClass(clazz, sort(fields));
        } else {
            throw new IllegalArgumentException("Missing entity annotation for class '" + clazz.getSimpleName() + "'.");
        }
    }

    private static @Mandatory boolean isEntity(@Mandatory Class clazz){
        return clazz.isAnnotationPresent(Entity.class);
    }

    private static @Mandatory Array<EntityField> sort(@Mandatory List<EntityField> fields){
        EntityField[] fieldArray = new EntityField[fields.count()];
        int i = 0;
        for(EntityField field : fields){
            fieldArray[i] = field;
            i++;
        }
        Arrays.sort(fieldArray, Comparator.comparing(EntityField::getName));
        return new Array<>(fieldArray);
    }
}
