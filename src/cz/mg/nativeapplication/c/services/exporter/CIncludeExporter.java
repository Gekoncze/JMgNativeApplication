package cz.mg.nativeapplication.c.services.exporter;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.c.entities.CInclude;


public @Service class CIncludeExporter {
    public String export(CInclude include){
        String openingBracket = include.local ? "\"" : "<";
        String closingBracket = include.local ? "\"" : ">";
        return "#include " + openingBracket + include.path + closingBracket;
    }
}
