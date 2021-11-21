package cz.mg.nativeapplication.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.list.List;


public @Service class ClassProvider {
    private static @Optional List<Class> classes;

    public void set(@Optional List<Class> classes){
        ClassProvider.classes = classes;
    }

    public @Mandatory List<Class> get(){
        if(classes != null){
            return classes;
        } else {
            throw new IllegalStateException("Missing classes");
        }
    }
}
