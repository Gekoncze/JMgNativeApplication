package cz.mg.nativeapplication.mg.services.creator;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.nativeapplication.mg.entities.components.MgLocation;
import cz.mg.nativeapplication.mg.entities.MgProject;
import cz.mg.nativeapplication.mg.services.MgAtomCreator;


public @Service class MgProjectCreator {
    public static String[] ATOMS_LOCATION = new String[]{ "cz", "mg", "atoms" };

    public @Mandatory MgProject create(@Mandatory String name){
        MgProject project = new MgProject();
        project.name = name;
        project.root = new MgLocation();
        project.root.name = "root";
        project.root.external = false;

        new MgAtomCreator().create(
            new MgLocationCreator().create(project.root, ATOMS_LOCATION)
        );

        return project;
    }
}
