package cz.mg.nativeapplication.sevices.entity;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;


@Service class EntityClassCreator {
    public @Mandatory EntityClass create(@Mandatory Class clazz){
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
                    fields.addLast(new EntityFieldCreator().create(clazz, field));
                }
                current = current.getSuperclass();
            }
            return new EntityClass(clazz, sort(fields));
        } else {
            throw new IllegalArgumentException("Missing entity annotation for class '" + clazz.getSimpleName() + "'.");
        }
    }

    private @Mandatory boolean isEntity(@Mandatory Class clazz){
        return clazz.isAnnotationPresent(Entity.class);
    }

    private @Mandatory Array<EntityField> sort(@Mandatory List<EntityField> fields){
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
