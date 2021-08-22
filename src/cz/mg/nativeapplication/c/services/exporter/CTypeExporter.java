package cz.mg.nativeapplication.c.services.exporter;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.c.entities.CType;


public @Service class CTypeExporter {
    public String export(CType type){
        String struct = type.structure ? "struct " : "";
        return struct + type.name + pointers(type.pointers);
    }

    private String pointers(int count){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < count; i++){
            s.append("*");
        }
        return s.toString();
    }
}
