package cz.mg.nativeapplication.entities.c;

import cz.mg.annotations.classes.Entity;
import cz.mg.collections.list.List;


public @Entity class CFile {
    public String name;
    public List<CComponent> components = new List<>();
}
