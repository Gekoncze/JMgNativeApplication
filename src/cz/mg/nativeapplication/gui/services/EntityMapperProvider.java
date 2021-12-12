package cz.mg.nativeapplication.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Optional;
import cz.mg.entity.mapper.Mapper;


public @Service class EntityMapperProvider {
    private static @Optional Mapper mapper;

    public void set(@Optional Mapper mapper) {
        EntityMapperProvider.mapper = mapper;
    }

    public Mapper get() {
        if(mapper != null){
            return mapper;
        } else {
            throw new IllegalStateException("Missing entity mapper.");
        }
    }
}
