package cz.mg.nativeapplication;

import cz.mg.collections.list.List;
import cz.mg.nativeapplication.entities.c.*;
import cz.mg.nativeapplication.entities.qt.QtProject;
import cz.mg.nativeapplication.entities.storage.Folder;
import cz.mg.nativeapplication.sevices.c.exporter.CProjectExporter;
import cz.mg.nativeapplication.sevices.qt.QtProjectCreator;
import cz.mg.nativeapplication.sevices.qt.QtProjectExporter;


public class CExportTest {
    public static void main(String[] args) {
        CProject cProject = new CProject();
        cProject.name = "CTestApplication";
        cProject.library = false;
        cProject.libraries = new List<>(
            "GL", "vulkan", "FooBar"
        );

        cProject.root = new CFolder();
        cProject.root.name = cProject.name;
        cProject.root.files.addLast(createHeaderFile());
        cProject.root.files.addLast(createSourceFile());

        Folder folder = new CProjectExporter().export(cProject);
        QtProject qtProject = new QtProjectCreator().create(cProject);
        folder.files.addLast(new QtProjectExporter().export(qtProject));

        new TempStorageSaver().save(folder);
    }

    private static CFile createHeaderFile(){
        CFile headerFile = new CFile();
        headerFile.name = "CTestStructure.h";
        headerFile.components.addLast(createGuardsBegin());
        headerFile.components.addLast(new CSeparator());
        headerFile.components.addLast(createInclude("stdio.h", false));
        headerFile.components.addLast(new CSeparator());
        headerFile.components.addLast(createDefine("DEBUG", null, "1"));
        headerFile.components.addLast(createDefine("create", new List<>("x"), "((x*) malloc(sizeof(x)))"));
        headerFile.components.addLast(new CSeparator());
        headerFile.components.addLast(createTypedef("unsigned int", "UInt32"));
        headerFile.components.addLast(new CSeparator());
        headerFile.components.addLast(createStructureDeclaration());
        headerFile.components.addLast(new CSeparator());
        headerFile.components.addLast(createStructureDefinition());
        headerFile.components.addLast(new CSeparator());
        headerFile.components.addLast(createFunctionDeclaration1());
        headerFile.components.addLast(createFunctionDeclaration2());
        headerFile.components.addLast(createFunctionDeclaration3());
        headerFile.components.addLast(new CSeparator());
        headerFile.components.addLast(createGuardsEnd());
        return headerFile;
    }

    private static CFile createSourceFile(){
        CFile sourceFile = new CFile();
        sourceFile.name = "CTestStructure.c";
        sourceFile.components.addLast(new CSeparator());
        sourceFile.components.addLast(createInclude("CTestStructure.h", true));
        sourceFile.components.addLast(createInclude("GL/gl.h", false));
        sourceFile.components.addLast(new CSeparator());
        sourceFile.components.addLast(createDefine("DEBUG", null, "0"));
        sourceFile.components.addLast(new CSeparator());
        sourceFile.components.addLast(createFunctionDefinition1());
        sourceFile.components.addLast(new CSeparator());
        sourceFile.components.addLast(createFunctionDefinition2());
        sourceFile.components.addLast(new CSeparator());
        sourceFile.components.addLast(createFunctionDefinition3());
        return sourceFile;
    }

    private static CGuardsBegin createGuardsBegin(){
        CGuardsBegin guardsBegin = new CGuardsBegin();
        guardsBegin.name = "CTESTSTRUCTURE_H";
        return guardsBegin;
    }

    private static CGuardsEnd createGuardsEnd(){
        return new CGuardsEnd();
    }

    private static CStructureDeclaration createStructureDeclaration(){
        CStructureDeclaration structureDeclaration = new CStructureDeclaration();
        structureDeclaration.name = "CTestStructure";
        return structureDeclaration;
    }

    private static CStructureDefinition createStructureDefinition(){
        CStructureDefinition structureDefinition = new CStructureDefinition();
        structureDefinition.declaration = createStructureDeclaration();
        structureDefinition.variables.addLast(createVariable(false, "UInt32", 2, "count"));
        structureDefinition.variables.addLast(createVariable(false, "Float64", 0, "value"));
        structureDefinition.variables.addLast(createVariable(true, "CTestStructure", 1, "parent"));
        return structureDefinition;
    }

    private static CVariable createVariable(boolean structure, String type, int pointers, String name){
        CVariable variable = new CVariable();
        variable.type = new CType();
        variable.type.structure = structure;
        variable.type.name = type;
        variable.type.pointers = pointers;
        variable.name = name;
        return variable;
    }

    private static CInclude createInclude(String path, boolean local){
        CInclude include = new CInclude();
        include.path = path;
        include.local = local;
        return include;
    }

    private static CDefine createDefine(String name, List<String> parameters, String definition){
        CDefine define = new CDefine();
        define.name = name;
        define.parameters = parameters;
        define.definition = definition;
        return define;
    }

    private static CTypedef createTypedef(String type, String alias){
        CTypedef typedef = new CTypedef();
        typedef.type = type;
        typedef.alias = alias;
        return typedef;
    }

    private static CFunctionDeclaration createFunctionDeclaration1(){
        CFunctionDeclaration declaration = new CFunctionDeclaration();
        declaration.name = "fooBar1";
        return declaration;
    }

    private static CFunctionDeclaration createFunctionDeclaration2(){
        CFunctionDeclaration declaration = new CFunctionDeclaration();
        declaration.name = "fooBar2";
        declaration.input.addLast(createVariable(false, "UInt32", 0, "id"));
        return declaration;
    }

    private static CFunctionDeclaration createFunctionDeclaration3(){
        CFunctionDeclaration declaration = new CFunctionDeclaration();
        declaration.name = "fooBar3";
        declaration.input.addLast(createVariable(false, "UInt32", 0, "id"));
        declaration.input.addLast(createVariable(false, "Float32", 0, "value"));
        declaration.output = createVariable(false, "UInt32", 0, "").type;
        return declaration;
    }

    private static CFunctionDefinition createFunctionDefinition1(){
        CFunctionDefinition definition = new CFunctionDefinition();
        definition.declaration = createFunctionDeclaration1();
        return definition;
    }

    private static CFunctionDefinition createFunctionDefinition2(){
        CFunctionDefinition definition = new CFunctionDefinition();
        definition.declaration = createFunctionDeclaration2();
        definition.commands.addLast(createReturnCommand("id"));
        return definition;
    }

    private static CFunctionDefinition createFunctionDefinition3(){
        CFunctionDefinition definition = new CFunctionDefinition();
        definition.declaration = createFunctionDeclaration3();
        definition.commands.addLast(createIfCommand(
            "id != 0",
            createWhileCommand(
                "id > 0",
                createDummyCommand(),
                createExpressionCommand("id--")
            )
        ));
        definition.commands.addLast(createElseCommand(
            createDummyCommand()
        ));
        definition.commands.addLast(createReturnCommand("id"));
        return definition;
    }

    private static CCommand createReturnCommand(String name){
        CCommand command = new CCommand();
        command.expression = "return " + name;
        return command;
    }

    private static CCommand createIfCommand(String condition, CCommand... subcommands){
        CBlockCommand command = new CBlockCommand();
        command.expression = "if(" + condition + ")";
        command.commands = new List<>();

        for(CCommand subcommand : subcommands){
            command.commands.addLast(subcommand);
        }
        return command;
    }

    private static CCommand createWhileCommand(String condition, CCommand... subcommands){
        CBlockCommand command = new CBlockCommand();
        command.expression = "while(" + condition + ")";
        command.commands = new List<>();
        for(CCommand subcommand : subcommands){
            command.commands.addLast(subcommand);
        }
        return command;
    }

    private static CCommand createElseCommand(CCommand... subcommands){
        CBlockCommand command = new CBlockCommand();
        command.expression = "else";
        command.commands = new List<>();
        for(CCommand subcommand : subcommands){
            command.commands.addLast(subcommand);
        }
        return command;
    }

    private static CCommand createDummyCommand(){
        return createExpressionCommand("// ...");
    }

    private static CCommand createExpressionCommand(String expression){
        CCommand command = new CCommand();
        command.expression = expression;
        return command;
    }
}
