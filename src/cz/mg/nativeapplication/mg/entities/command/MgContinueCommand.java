package cz.mg.nativeapplication.mg.entities.command;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;


public @Entity class MgContinueCommand extends MgCommand {
    public @Value String target;
}
