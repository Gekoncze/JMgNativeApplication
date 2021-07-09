package cz.mg.nativeapplication.entities.mg.components;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.entities.mg.parts.MgOperator;
import cz.mg.nativeapplication.entities.mg.command.MgCommand;


public @Entity class MgFunction extends MgComponent {
    public @Part List<MgVariable> input = new List<>();
    public @Part MgVariable output;
    public @Part List<MgCommand> commands = new List<>();
    public @Part MgOperator operator;
}
