package cz.mg.nativeapplication;

import cz.mg.nativeapplication.sevices.c.creator.CProjectCreator;
import cz.mg.nativeapplication.sevices.c.exporter.CProjectExporter;
import cz.mg.nativeapplication.sevices.mg.storage.MgProjectLoader;
import cz.mg.nativeapplication.sevices.mg.storage.MgProjectSaver;

import java.nio.file.Paths;


public class MgCompleteTest {
    private static final String FILENAME = "Test.mg";
    private static final String PATH = Paths.get(TempStorageSaver.PATH.toString(), FILENAME).toString();

    public static void main(String[] args) {
        new MgProjectSaver().save(
            PATH, new MgTestProjectCreator().create()
        );

        new TempStorageSaver().save(
            new CProjectExporter().export(
                new CProjectCreator().create(
                    new MgProjectLoader().load(PATH)
                )
            )
        );
    }
}
