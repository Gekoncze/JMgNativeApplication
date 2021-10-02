package cz.mg.nativeapplication.mg.entities.components;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.mg.Mg;


public @Mg @Entity class MgLocation extends MgComponent {
    public @Value Boolean external;
    public @Part List<MgComponent> components = new List<>();
    public @Link List<MgComponent> links = new List<>(); // TODO - delete me now
}
