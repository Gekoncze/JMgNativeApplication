package cz.mg.nativeapplication.c.entities;

import cz.mg.annotations.classes.Entity;
import cz.mg.collections.list.List;


public @Entity class CFunctionDefinition implements CComponent {
    public CFunctionDeclaration declaration;
    public List<CCommand> commands = new List<>();
}
