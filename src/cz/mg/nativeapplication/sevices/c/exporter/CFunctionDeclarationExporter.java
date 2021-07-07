package cz.mg.nativeapplication.sevices.c.exporter;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.c.CFunctionDeclaration;
import cz.mg.nativeapplication.entities.c.CVariable;


public @Service class CFunctionDeclarationExporter {
    public String export(CFunctionDeclaration functionDeclaration){
        StringBuilder s = new StringBuilder();
        if(functionDeclaration.output != null){
            s.append(new CTypeExporter().export(functionDeclaration.output));
        } else {
            s.append("void");
        }
        s.append(" ");
        s.append(functionDeclaration.name);
        s.append("(");
        for(CVariable input : functionDeclaration.input){
            s.append(new CVariableExporter().export(input));
            if(input != functionDeclaration.input.getLast()){
                s.append(", ");
            }
        }
        s.append(")");
        return s.toString();
    }
}
