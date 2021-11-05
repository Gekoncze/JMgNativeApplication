package cz.mg.nativeapplication.mg.entities.components;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.nativeapplication.mg.entities.annotations.Required;
import cz.mg.nativeapplication.mg.entities.parts.MgOperator;
import cz.mg.nativeapplication.mg.entities.command.MgCommand;


public @Mg @Entity class MgFunction extends MgComponent {
    public @Required @Part List<MgVariable> input = new List<>();
    public @Part MgVariable output;
    public @Required @Part List<MgCommand> commands = new List<>();
    public @Part MgOperator operator;
}
