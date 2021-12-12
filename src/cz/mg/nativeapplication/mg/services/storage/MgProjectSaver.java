package cz.mg.nativeapplication.mg.services.storage;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.storage.ElementTableWriter;
import cz.mg.nativeapplication.gui.services.ProjectMapperProvider;
import cz.mg.nativeapplication.mg.entities.MgProject;
import cz.mg.sql.light.connection.SqlConnection;
import cz.mg.sql.light.connection.connections.SqliteConnection;

import java.nio.file.Path;


public @Service class MgProjectSaver {
    private final @Mandatory @Shared ProjectMapperProvider projectMapperProvider = new ProjectMapperProvider();
    private final @Mandatory @Shared ElementTableWriter elementTableWriter = new ElementTableWriter();

    public void save(@Mandatory Path path, @Optional MgProject project){
        try(SqlConnection connection = new SqliteConnection(path.toString())){
            connection.begin();

            elementTableWriter.write(
                connection,
                projectMapperProvider.get().map(project)
            );

            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
