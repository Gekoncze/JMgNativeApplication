package cz.mg.nativeapplication.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Optional;
import cz.mg.entity.mapper.Mapper;
import cz.mg.nativeapplication.mg.entities.MgProject;


public @Service class ProjectMapperProvider {
    private static @Optional Mapper<MgProject> mapper;

    public void set(@Optional Mapper<MgProject> mapper) {
        ProjectMapperProvider.mapper = mapper;
    }

    public Mapper<MgProject> get() {
        if(mapper != null){
            return mapper;
        } else {
            throw new IllegalStateException("Missing project mapper.");
        }
    }
}
