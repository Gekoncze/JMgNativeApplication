package cz.mg.nativeapplication.gui;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.classdetector.ClassReader;
import cz.mg.entity.EntityClassInitializer;
import cz.mg.entity.mapper.Mapper;
import cz.mg.entity.mapper.MapperFactory;


public @Service class Initialization {
    private final @Mandatory @Shared ClassReader classReader = new ClassReader();
    private final @Mandatory @Shared EntityClassInitializer entityClassInitializer = new EntityClassInitializer();
    private final @Mandatory @Shared MapperFactory mapperFactory = new MapperFactory();

    public void init(){
        entityClassInitializer.init(
            classReader.read(
                Application.class.getResourceAsStream("Classes.txt")
            )
        );
    }

    public @Mandatory Mapper createMapper(){
        return mapperFactory.create(
            classReader.read(
                Application.class.getResourceAsStream("Classes.txt")
            )
        );
    }
}
