package cz.mg.nativeapplication.mg.services.storage;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.mapper.Element;
import cz.mg.entity.storage.ElementTableReader;
import cz.mg.nativeapplication.gui.services.EntityMapperProvider;
import cz.mg.sql.light.connection.SqlConnection;
import cz.mg.sql.light.connection.connections.SqliteConnection;


public @Service class EntityReader {
    private final @Mandatory @Shared EntityMapperProvider entityMapperProvider = new EntityMapperProvider();
    private final @Mandatory @Shared ElementTableReader elementTableReader = new ElementTableReader();

    public @Mandatory Object readMandatory(@Mandatory String path){
        Object entity = read(path);
        if(entity != null){
            return entity;
        } else {
            throw new NullPointerException();
        }
    }

    public @Optional Object read(@Mandatory String path){
        return entityMapperProvider.get().unmap(
            loadElements(path)
        );
    }

    private @Mandatory List<Element> loadElements(@Mandatory String path){
        try(SqlConnection connection = new SqliteConnection(path)){
            connection.begin();
            return elementTableReader.read(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
