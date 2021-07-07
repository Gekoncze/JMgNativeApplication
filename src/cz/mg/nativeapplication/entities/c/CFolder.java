package cz.mg.nativeapplication.entities.c;

import cz.mg.annotations.classes.Entity;
import cz.mg.collections.list.List;


public @Entity class CFolder {
    public String name;
    public List<CFolder> folders = new List<>();
    public List<CFile> files = new List<>();
}
