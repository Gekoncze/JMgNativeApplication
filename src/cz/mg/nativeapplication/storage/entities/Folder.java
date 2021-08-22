package cz.mg.nativeapplication.storage.entities;

import cz.mg.collections.list.List;


public class Folder {
    public String name;
    public List<Folder> folders = new List<>();
    public List<File> files = new List<>();
}
