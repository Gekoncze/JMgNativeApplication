package cz.mg.nativeapplication.c.entities;

import cz.mg.annotations.classes.Entity;
import cz.mg.collections.list.List;


public @Entity class CFunctionDeclaration implements CComponent {
    public String name;
    public List<CVariable> input = new List<>();
    public CType output;
}
