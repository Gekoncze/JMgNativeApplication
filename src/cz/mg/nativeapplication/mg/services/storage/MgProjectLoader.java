package cz.mg.nativeapplication.mg.services.storage;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.mapper.Element;
import cz.mg.entity.storage.ElementTableReader;
import cz.mg.nativeapplication.gui.services.ProjectMapperProvider;
import cz.mg.nativeapplication.mg.entities.MgProject;
import cz.mg.sql.light.connection.SqlConnection;
import cz.mg.sql.light.connection.connections.SqliteConnection;

import java.nio.file.Path;


public @Service class MgProjectLoader {
    private final @Mandatory @Shared ProjectMapperProvider projectMapperProvider = new ProjectMapperProvider();
    private final @Mandatory @Shared ElementTableReader elementTableReader = new ElementTableReader();

    public @Optional MgProject load(@Mandatory Path path){
        return projectMapperProvider.get().unmap(
            loadElements(path)
        );
    }

    private @Mandatory List<Element> loadElements(@Mandatory Path path){
        try(SqlConnection connection = new SqliteConnection(path.toString())){
            connection.begin();
            return elementTableReader.read(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
