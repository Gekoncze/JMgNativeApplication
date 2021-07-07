package cz.mg.nativeapplication.mapper;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.list.List;
import cz.mg.collections.map.Map;


public @Utility class Mapper<T> {
    private final @Mandatory List<ObjectMapper> objectMappers;

    public Mapper(@Mandatory List<ObjectMapper> objectMappers) {
        this.objectMappers = objectMappers;
    }

    public @Mandatory List<Entity> map(@Optional T object){
        List<Entity> entities = new List<>();
        Map<Object, Integer> cache = new Map<>();
        map(entities, cache, object);
        return entities;
    }

    private @Optional Integer map(@Mandatory List<Entity> entities, @Mandatory Map<Object, Integer> cache, @Optional Object object){
        if(object == null){
            return null;
        }

        if(cache.get(object) != null){
            return cache.get(object);
        }

        int id = entities.count();

        ObjectMapper objectMapper = findMapperForObject(object);
        Entity entity = new Entity();
        entity.name = objectMapper.getName();
        entity.value = objectMapper.getValue(object);
        entity.fields = new List<>();

        entities.addLast(entity);
        cache.set(object, id);

        for(Object child : objectMapper.getFields(object)){
            entity.fields.addLast(map(entities, cache, child));
        }

        return id;
    }

    public @Optional T unmap(@Mandatory List<Entity> entities){
        Map<Integer, Object> cache = new Map<>();
        return (T) unmap(entities, cache, 0);
    }

    private @Optional Object unmap(@Mandatory List<Entity> entities, @Mandatory Map<Integer, Object> cache, @Optional Integer id){
        if(id == null){
            return null;
        }

        if(cache.get(id) != null){
            return cache.get(id);
        }

        Entity entity = entities.get(id);
        if(entity == null){
            throw new NullPointerException("Could not find entity with id " + id + ".");
        }

        ObjectMapper objectMapper = findMapperForEntity(entity);
        Object object = objectMapper.create(entity.value);

        cache.set(id, object);

        List<Object> children = new List<>();
        for(Integer entityField : entity.fields){
            children.addLast(unmap(entities, cache, entityField));
        }
        objectMapper.setFields(object, children);

        return object;
    }

    private @Mandatory ObjectMapper findMapperForObject(@Mandatory Object object){
        for(ObjectMapper objectMapper : objectMappers){
            if(objectMapper.isApplicable(object)){
                return objectMapper;
            }
        }
        throw new UnsupportedOperationException("Could not find matching object mapper for object of type '" + object.getClass().getSimpleName() + "'.");
    }

    private @Mandatory ObjectMapper findMapperForEntity(@Mandatory Entity entity){
        for(ObjectMapper objectMapper : objectMappers){
            if(objectMapper.getName().equals(entity.name)){
                return objectMapper;
            }
        }
        throw new UnsupportedOperationException("Could not find matching object mapper for entity '" + entity.name + "'.");
    }
}
