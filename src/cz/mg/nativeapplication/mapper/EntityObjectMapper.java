package cz.mg.nativeapplication.mapper;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.sevices.EntityClass;
import cz.mg.nativeapplication.sevices.EntityField;


public @Utility class EntityObjectMapper<T> implements ObjectMapper<T> {
    private final @Mandatory @Part EntityClass<T> entityClass;

    public EntityObjectMapper(@Mandatory Class<T> clazz) {
        this.entityClass = EntityClass.create(clazz);
    }

    @Override
    public @Mandatory boolean isApplicable(@Mandatory Object object) {
        return object.getClass() == entityClass.getClazz();
    }

    @Override
    public @Mandatory String getName() {
        return entityClass.getName();
    }

    @Override
    public T create(@Optional String value) {
        return entityClass.newInstance();
    }

    @Override
    public @Optional String getValue(@Mandatory T object) {
        return null;
    }

    @Override
    public @Mandatory List<Object> getFields(@Mandatory T object) {
        List<Object> fieldValues = new List<>();
        for(EntityField field : entityClass.getFields()){
            fieldValues.addLast(field.get(object));
        }
        return fieldValues;
    }

    @Override
    public void setFields(@Mandatory T object, @Mandatory List<Object> fieldValues) {
        int i = 0;
        for(Object fieldValue : fieldValues){
            entityClass.getFields().get(i).set(object, fieldValue);
            i++;
        }
    }
}
