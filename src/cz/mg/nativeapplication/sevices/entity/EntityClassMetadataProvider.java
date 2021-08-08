package cz.mg.nativeapplication.sevices.entity;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;


public @Service class EntityClassMetadataProvider {
    public @Mandatory EntityClass get(@Mandatory Class clazz){
        return EntityClassMetadata.getInstance().get(clazz);
    }
}
