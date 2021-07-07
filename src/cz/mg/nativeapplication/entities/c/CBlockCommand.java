package cz.mg.nativeapplication.entities.c;

import cz.mg.annotations.classes.Entity;
import cz.mg.collections.list.List;


public @Entity class CBlockCommand extends CCommand {
    public List<CCommand> commands = new List<>();
}
