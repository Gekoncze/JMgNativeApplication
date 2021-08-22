package cz.mg.nativeapplication.mg.services.storage;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;
import cz.mg.objectmapper.Entity;
import cz.mg.sql.light.builder.SqlBuilder;
import cz.mg.sql.light.connection.SqlConnection;
import cz.mg.sql.light.connection.SqlResult;
import cz.mg.sql.light.types.SqliteTypes;


public @Utility class MgEntityTable {
    private static final String TABLE_NAME = "Entity";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String VALUE = "value";

    private static MgEntityTable instance = null;

    public static MgEntityTable getInstance(){
        if(instance == null) instance = new MgEntityTable();
        return instance;
    }

    public MgEntityTable() {
    }

    public void createOrReplace(@Mandatory SqlConnection connection){
        if(exists(connection)){
            delete(connection);
        }

        create(connection);
    }

    private boolean exists(@Mandatory SqlConnection connection){
        return (int) connection.executeQuery(
            new SqlBuilder()
                .readTable(TABLE_NAME)
                .build()
        ).getSingleResult().get(0) > 0;
    }

    private void delete(@Mandatory SqlConnection connection){
        connection.executeDdl(
            new SqlBuilder()
                .deleteTable(TABLE_NAME)
                .build()
        );
    }

    private void create(@Mandatory SqlConnection connection){
        connection.executeDdl(
            new SqlBuilder()
                .createTable(TABLE_NAME)
                .column(ID, SqliteTypes.INTEGER)
                .column(NAME, SqliteTypes.TEXT)
                .column(VALUE, SqliteTypes.TEXT)
                .build()
        );
    }

    public int rowCount(@Mandatory SqlConnection connection){
        return (int) connection.executeQuery(
            new SqlBuilder()
                .readRow(TABLE_NAME)
                .column("count(*)")
                .build()
        ).getSingleResult().get(0);
    }

    public void createRow(@Mandatory SqlConnection connection, int entityId, @Mandatory Entity entity){
        connection.executeDml(
            new SqlBuilder()
                .createRow(TABLE_NAME)
                .column(ID, entityId)
                .column(NAME, entity.name)
                .column(VALUE, entity.value)
                .build()
        );
    }

    public @Mandatory Entity readRow(@Mandatory SqlConnection connection, int entityId){
        return createEntity(
            connection.executeQuery(
                new SqlBuilder()
                    .readRow(TABLE_NAME)
                    .column(NAME)
                    .column(VALUE)
                    .condition(ID, entityId)
                    .build()
            ).getSingleResult()
        );
    }

    private @Mandatory Entity createEntity(@Mandatory SqlResult result){
        Entity entity = new Entity();
        entity.name = (String) result.get(NAME);
        entity.value = (String) result.get(VALUE);
        entity.fields = new List<>();
        return entity;
    }
}
