package cz.mg.nativeapplication.sevices.mg.storage;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.sql.light.builder.SqlBuilder;
import cz.mg.sql.light.connection.SqlConnection;
import cz.mg.sql.light.types.SqliteTypes;


public @Utility class MgEntityFieldTable {
    private static final String TABLE_NAME = "EntityField";
    private static final String PARENT_ID = "parentId";
    private static final String ID = "id";
    private static final String TARGET = "target";

    private static MgEntityFieldTable instance = null;

    public static MgEntityFieldTable getInstance(){
        if(instance == null) instance = new MgEntityFieldTable();
        return instance;
    }

    public MgEntityFieldTable() {
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
                .column(PARENT_ID, SqliteTypes.INTEGER)
                .column(ID, SqliteTypes.INTEGER)
                .column(TARGET, SqliteTypes.INTEGER)
                .build()
        );
    }

    public int rowCount(@Mandatory SqlConnection connection, int parentId){
        return (int) connection.executeQuery(
            new SqlBuilder()
                .readRow(TABLE_NAME)
                .column("count(*)")
                .condition(PARENT_ID, parentId)
                .build()
        ).getSingleResult().get(0);
    }

    public void createRow(@Mandatory SqlConnection connection, int parentId, int fieldId, @Optional Integer target){
        connection.executeDml(
            new SqlBuilder()
                .createRow(TABLE_NAME)
                .column(PARENT_ID, parentId)
                .column(ID, fieldId)
                .column(TARGET, target)
                .build()
        );
    }

    public @Optional Integer readRow(@Mandatory SqlConnection connection, int parentId, int id){
        return (Integer) connection.executeQuery(
            new SqlBuilder()
                .readRow(TABLE_NAME)
                .column(TARGET)
                .condition(PARENT_ID, parentId)
                .condition(ID, id)
                .build()
        ).getSingleResult().get(0);
    }
}
