package cz.mg.nativeapplication.c.entities;

import cz.mg.annotations.classes.Entity;
import cz.mg.collections.list.List;


public @Entity class CProject {
    public String name;
    public Boolean library;
    public CFolder root;
    public List<String> libraries = new List<>();
}
