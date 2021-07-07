package cz.mg.nativeapplication.sevices.mg.creator;

import cz.mg.nativeapplication.entities.mg.components.MgLocation;
import cz.mg.nativeapplication.entities.mg.MgProject;
import cz.mg.nativeapplication.sevices.mg.MgAtomCreator;


public class MgProjectCreator {
    public static String[] ATOMS_LOCATION = new String[]{ "cz", "mg", "atoms" };

    public MgProject create(String name){
        MgProject project = new MgProject();
        project.name = name;
        project.root = new MgLocation();
        project.root.name = "root";

        new MgAtomCreator().create(
            new MgLocationCreator().create(project.root, ATOMS_LOCATION)
        );

        return project;
    }
}
