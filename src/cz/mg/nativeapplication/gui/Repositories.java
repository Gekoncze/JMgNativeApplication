package cz.mg.nativeapplication.gui;

import cz.mg.entity.EntityClassRepositoryBuilder;
import cz.mg.entity.EntityClasses;
import cz.mg.nativeapplication.mg.entities.parts.MgOperator;
import cz.mg.nativeapplication.mg.services.MgClassProvider;
import cz.mg.objectmapper.ObjectMapperRepositoryBuilder;
import cz.mg.objectmapper.ObjectMappers;


public class Repositories {
    public static void init(){
        initEntityClasses();
        initObjectMappers();
    }

    private static void initEntityClasses(){
        EntityClassRepositoryBuilder builder = new EntityClassRepositoryBuilder();
        for(Class clazz : new MgClassProvider().get()){
            builder.addClass(clazz);
        }
        EntityClasses.setRepository(builder.build());
    }

    private static void initObjectMappers(){
        ObjectMapperRepositoryBuilder builder = new ObjectMapperRepositoryBuilder();
        builder.addDefault();
        for(Class clazz : EntityClasses.getRepository().getAvailableClasses()){
            builder.addEntity(clazz);
        }
        builder.addEnum(MgOperator.Type.class);
        ObjectMappers.setRepository(builder.build());
    }

    private Repositories(){
    }
}
