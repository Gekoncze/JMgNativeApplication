package cz.mg.nativeapplication.sevices.c.exporter;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.c.CInclude;


public @Service class CIncludeExporter {
    public String export(CInclude include){
        String openingBracket = include.local ? "\"" : "<";
        String closingBracket = include.local ? "\"" : ">";
        return "#include " + openingBracket + include.path + closingBracket;
    }
}
