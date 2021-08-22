package cz.mg.nativeapplication.c.services.exporter;

import cz.mg.annotations.classes.Service;
import cz.mg.collections.ToStringBuilder;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.c.entities.CDefine;


public @Service class CDefineExporter {
    public String export(CDefine define){
        return "#define " + define.name + exportParameters(define.parameters) + " " + define.definition;
    }

    private String exportParameters(List<String> parameters){
        if(parameters == null) return "";
        if(parameters.isEmpty()) return "";
        return new ToStringBuilder<>(parameters)
            .prefix("(")
            .delim(", ")
            .postfix(")")
            .build();
    }
}
