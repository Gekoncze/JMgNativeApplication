package cz.mg.nativeapplication.mg.entities;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.nativeapplication.mg.entities.annotations.Name;
import cz.mg.nativeapplication.mg.entities.annotations.Required;
import cz.mg.nativeapplication.mg.entities.components.MgFunction;
import cz.mg.nativeapplication.mg.entities.components.MgLocation;


public @Mg @Entity class MgProject {
    public @Required @Name @Value String name;
    public @Link MgFunction main;
    public @Required @Part MgLocation root;
    public @Required @Value List<String> nativeLibraries = new List<>();
}
