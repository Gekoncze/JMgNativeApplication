package cz.mg.nativeapplication.c.services.exporter;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.c.entities.*;
import cz.mg.nativeapplication.storage.entities.File;


public @Service class CFileExporter {
    public File export(CFile cFile){
        File file = new File();
        file.name = cFile.name;

        for(CComponent component : cFile.components){
            if(component instanceof CSeparator){
                separate(file);
            }

            else if(component instanceof CGuardsBegin){
                CGuardsBegin guard = (CGuardsBegin) component;
                file.lines.addLast("#ifndef " + guard.name);
                file.lines.addLast("#define " + guard.name);
            }

            else if(component instanceof CGuardsEnd){
                file.lines.addLast("#endif");
            }

            else if(component instanceof CInclude){
                CInclude include = (CInclude) component;
                file.lines.addLast(new CIncludeExporter().export(include));
            }

            else if(component instanceof CDefine){
                CDefine define = (CDefine) component;
                file.lines.addLast(new CDefineExporter().export(define));
            }

            else if(component instanceof CTypedef){
                CTypedef typedef = (CTypedef) component;
                file.lines.addLast(new CTypedefExporter().export(typedef));
            }

            else if(component instanceof CStructureDeclaration){
                CStructureDeclaration structureDeclaration = (CStructureDeclaration) component;
                file.lines.addLast(new CStructureDeclarationExporter().export(structureDeclaration) + ";");
            }

            else if(component instanceof CStructureDefinition){
                CStructureDefinition structureDefinition = (CStructureDefinition) component;
                file.lines.addCollectionLast(new CStructureDefinitionExporter().export(structureDefinition));
            }

            else if(component instanceof CFunctionDeclaration){
                CFunctionDeclaration functionDeclaration = (CFunctionDeclaration) component;
                file.lines.addLast(new CFunctionDeclarationExporter().export(functionDeclaration) + ";");
            }

            else if(component instanceof CFunctionDefinition){
                CFunctionDefinition functionDefinition = (CFunctionDefinition) component;
                file.lines.addCollectionLast(new CFunctionDefinitionExporter().export(functionDefinition));
            }

            else {
                throw new UnsupportedOperationException("Class " + component.getClass().getSimpleName() + " is not supported in c file exporter.");
            }
        }

        return file;
    }


    private void separate(File file){
        if(file.lines.isEmpty()) return;
        if(!file.lines.getLast().equals("")){
            file.lines.addLast("");
        }
    }
}
