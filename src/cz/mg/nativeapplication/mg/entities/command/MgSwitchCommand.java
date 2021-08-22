package cz.mg.nativeapplication.mg.entities.command;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.mg.Mg;


public @Mg @Entity class MgSwitchCommand extends MgCommand {
    public @Part List<MgCaseCommand> cases = new List<>();
}
