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


public @Service class MgProjectLoader {
    private final @Mandatory @Shared ProjectMapperProvider projectMapperProvider = new ProjectMapperProvider();

    public @Optional MgProject load(@Mandatory Path path){
        MgElementTable entityTable = MgElementTable.getInstance();
        MgElementFieldTable fieldTable = MgElementFieldTable.getInstance();

        List<Element> elements = new List<>();

        try(SqlConnection connection = new SqliteConnection(path.toString())){
            connection.begin();

            int entityCount = entityTable.rowCount(connection);
            for(int entityId = 0; entityId < entityCount; entityId++){
                Element element = entityTable.readRow(connection, entityId);
                int fieldCount = fieldTable.rowCount(connection, entityId);
                for(int fieldId = 0; fieldId < fieldCount; fieldId++){
                    element.fields.addLast(fieldTable.readRow(connection, entityId, fieldId));
                }
                elements.addLast(element);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return projectMapperProvider.get().unmap(elements);
    }
}
