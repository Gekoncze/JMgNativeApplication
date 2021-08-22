package cz.mg.nativeapplication.c.entities;

import cz.mg.annotations.classes.Entity;
import cz.mg.collections.list.List;


public @Entity class CBlockCommand extends CCommand {
    public List<CCommand> commands = new List<>();
}
