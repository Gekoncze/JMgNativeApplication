package cz.mg.nativeapplication.sevices.entity;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;


public @Service class EntityClassMetadataProvider {
    private static @Optional @Part EntityClassMetadata instance;

    private synchronized static @Mandatory EntityClassMetadata getInstance() {
        if(instance == null){
            instance = new EntityClassMetadataCreator().create();
        }

        return instance;
    }

    public @Mandatory EntityClass get(@Mandatory Class clazz){
        return getInstance().get(clazz);
    }
}
