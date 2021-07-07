package cz.mg.nativeapplication.entities.mg.command;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;


public @Entity class MgSwitchCommand extends MgCommand {
    public @Part List<MgCaseCommand> cases = new List<>();
}
