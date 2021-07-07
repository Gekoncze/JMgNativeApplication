package cz.mg.nativeapplication.entities.mg;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.entities.mg.components.MgFunction;
import cz.mg.nativeapplication.entities.mg.components.MgLocation;


public @Entity class MgProject {
    public @Value String name;
    public @Link MgFunction main;
    public @Part MgLocation root;
    public @Part List<String> nativeLibraries = new List<>();
}
