package cz.mg.nativeapplication.entities.mg.command;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;


public @Entity class MgContinueCommand extends MgCommand {
    public @Value String target;
}
