package cz.mg.nativeapplication.gui;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.classdetector.ClassReader;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClassInitializer;
import cz.mg.entity.mapper.MapperFactory;
import cz.mg.nativeapplication.gui.services.EntityMapperProvider;


public @Utility class Initialization {
    private final @Mandatory @Shared ClassReader classReader = new ClassReader();
    private final @Mandatory @Shared EntityClassInitializer entityClassInitializer = new EntityClassInitializer();
    private final @Mandatory @Shared EntityMapperProvider entityMapperProvider = new EntityMapperProvider();
    private final @Mandatory @Shared MapperFactory mapperFactory = new MapperFactory();

    public Initialization() {
    }

    public void init(){
        List<Class> classes = loadClasses();
        initEntityClasses(classes);
        initObjectMappers(classes);
    }

    private @Mandatory List<Class> loadClasses(){
        return classReader.read(Application.class.getResourceAsStream("Classes.txt"));
    }

    private void initEntityClasses(@Mandatory List<Class> classes){
        entityClassInitializer.init(classes);
    }

    private void initObjectMappers(@Mandatory List<Class> classes){
        entityMapperProvider.set(mapperFactory.create(classes));
    }
}
