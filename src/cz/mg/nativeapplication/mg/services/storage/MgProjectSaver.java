package cz.mg.nativeapplication.mg.services.storage;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.mapper.Element;
import cz.mg.nativeapplication.gui.services.ProjectMapperProvider;
import cz.mg.nativeapplication.mg.entities.MgProject;
import cz.mg.sql.light.connection.SqlConnection;
import cz.mg.sql.light.connection.connections.SqliteConnection;

import java.nio.file.Path;


public @Service class MgProjectSaver {
    private final @Mandatory @Shared ProjectMapperProvider projectMapperProvider = new ProjectMapperProvider();

    public void save(@Mandatory Path path, @Optional MgProject project){
        MgElementTable entityTable = MgElementTable.getInstance();
        MgElementFieldTable fieldTable = MgElementFieldTable.getInstance();

        try(SqlConnection connection = new SqliteConnection(path.toString())){
            connection.begin();

            List<Element> elements = projectMapperProvider.get().map(project);

            entityTable.createOrReplace(connection);
            fieldTable.createOrReplace(connection);

            int entityId = 0;
            for(Element element : elements){
                entityTable.createRow(connection, entityId, element);

                int fieldId = 0;
                for(Integer field : element.fields){
                    fieldTable.createRow(connection, entityId, fieldId, field);
                    fieldId++;
                }

                entityId++;
            }

            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
