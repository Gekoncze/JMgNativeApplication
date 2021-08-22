package cz.mg.nativeapplication.c.entities;

import cz.mg.annotations.classes.Entity;
import cz.mg.collections.list.List;


public @Entity class CStructureDefinition implements CComponent {
    public CStructureDeclaration declaration;
    public List<CVariable> variables = new List<>();
}
