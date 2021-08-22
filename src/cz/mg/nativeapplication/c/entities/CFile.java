package cz.mg.nativeapplication.c.entities;

import cz.mg.annotations.classes.Entity;
import cz.mg.collections.list.List;


public @Entity class CFile {
    public String name;
    public List<CComponent> components = new List<>();
}
