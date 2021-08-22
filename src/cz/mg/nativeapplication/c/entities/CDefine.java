package cz.mg.nativeapplication.c.entities;

import cz.mg.annotations.classes.Entity;
import cz.mg.collections.list.List;


public @Entity class CDefine implements CComponent {
    public String name;
    public List<String> parameters = new List<>();
    public String definition;
}
