package cz.mg.nativeapplication.entities.c;

import cz.mg.annotations.classes.Entity;
import cz.mg.collections.list.List;


public @Entity class CStructureDefinition implements CComponent {
    public CStructureDeclaration declaration;
    public List<CVariable> variables = new List<>();
}
