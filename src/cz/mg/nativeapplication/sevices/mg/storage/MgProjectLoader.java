package cz.mg.nativeapplication.sevices.mg.storage;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.entities.mg.MgProject;
import cz.mg.objectmapper.Entity;
import cz.mg.objectmapper.Mapper;
import cz.mg.sql.light.connection.SqlConnection;
import cz.mg.sql.light.connection.connections.SqliteConnection;

import java.nio.file.Path;


public @Service class MgProjectLoader {
    private final @Mandatory @Shared Mapper<MgProject> mapper = new Mapper<>();

    public @Optional MgProject load(@Mandatory Path path){
        MgEntityTable entityTable = MgEntityTable.getInstance();
        MgEntityFieldTable fieldTable = MgEntityFieldTable.getInstance();

        List<Entity> entities = new List<>();

        try(SqlConnection connection = new SqliteConnection(path.toString())){
            connection.begin();

            int entityCount = entityTable.rowCount(connection);
            for(int entityId = 0; entityId < entityCount; entityId++){
                Entity entity = entityTable.readRow(connection, entityId);
                int fieldCount = fieldTable.rowCount(connection, entityId);
                for(int fieldId = 0; fieldId < fieldCount; fieldId++){
                    entity.fields.addLast(fieldTable.readRow(connection, entityId, fieldId));
                }
                entities.addLast(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return mapper.unmap(entities);
    }
}
