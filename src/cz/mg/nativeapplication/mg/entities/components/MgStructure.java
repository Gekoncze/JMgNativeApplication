package cz.mg.nativeapplication.mg.entities.components;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.nativeapplication.mg.entities.annotations.Required;


public @Mg @Entity class MgStructure extends MgType {
    public @Required @Part List<MgVariable> variables = new List<>();
    public @Required @Value Boolean memoryManagement;
}
