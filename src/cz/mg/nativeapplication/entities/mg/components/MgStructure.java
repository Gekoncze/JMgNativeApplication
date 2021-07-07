package cz.mg.nativeapplication.entities.mg.components;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.entities.mg.parts.MgVariable;


public @Entity class MgStructure extends MgType {
    public @Part List<MgVariable> variables = new List<>();
    public @Value boolean withMemoryManagement;
}
