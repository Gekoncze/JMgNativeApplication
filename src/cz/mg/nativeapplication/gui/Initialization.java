package cz.mg.nativeapplication.gui;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.classdetector.ClassReader;
import cz.mg.entity.EntityClassInitializer;
import cz.mg.entity.mapper.MapperFactory;
import cz.mg.nativeapplication.gui.services.ClassProvider;
import cz.mg.nativeapplication.gui.services.ProjectMapperProvider;


public @Utility class Initialization {
    private final @Mandatory @Shared ClassReader classReader = new ClassReader();
    private final @Mandatory @Shared ClassProvider classProvider = new ClassProvider();
    private final @Mandatory @Shared EntityClassInitializer entityClassInitializer = new EntityClassInitializer();
    private final @Mandatory @Shared ProjectMapperProvider projectMapperProvider = new ProjectMapperProvider();
    private final @Mandatory @Shared MapperFactory mapperFactory = new MapperFactory();

    public Initialization() {
    }

    public void init(){
        initClasses();
        initEntityClasses();
        initObjectMappers();
    }

    private void initClasses(){
        classProvider.set(classReader.read(Application.class.getResourceAsStream("Classes.txt")));
    }

    private void initEntityClasses(){
        entityClassInitializer.init(classProvider.get());
    }

    private void initObjectMappers(){
        projectMapperProvider.set(mapperFactory.create(classProvider.get()));
    }
}
