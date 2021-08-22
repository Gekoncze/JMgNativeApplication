package cz.mg.nativeapplication.mg.services.storage;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.mg.entities.MgProject;
import cz.mg.objectmapper.Entity;
import cz.mg.objectmapper.Mapper;
import cz.mg.sql.light.connection.SqlConnection;
import cz.mg.sql.light.connection.connections.SqliteConnection;

import java.nio.file.Path;


public @Service class MgProjectSaver {
    private final @Mandatory @Shared Mapper<MgProject> mapper = new Mapper<>();

    public void save(@Mandatory Path path, @Optional MgProject project){
        MgEntityTable entityTable = MgEntityTable.getInstance();
        MgEntityFieldTable fieldTable = MgEntityFieldTable.getInstance();

        try(SqlConnection connection = new SqliteConnection(path.toString())){
            connection.begin();

            List<Entity> entities = mapper.map(project);

            entityTable.createOrReplace(connection);
            fieldTable.createOrReplace(connection);

            int entityId = 0;
            for(Entity entity : entities){
                entityTable.createRow(connection, entityId, entity);

                int fieldId = 0;
                for(Integer field : entity.fields){
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
