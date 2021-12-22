package other;

import cz.mg.nativeapplication.c.services.creator.CProjectCreator;
import cz.mg.nativeapplication.c.services.exporter.CProjectExporter;
import cz.mg.nativeapplication.gui.Initialization;
import cz.mg.nativeapplication.mg.entities.MgProject;
import cz.mg.entity.storage.EntityReader;
import cz.mg.entity.storage.EntityWriter;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;

import java.util.Objects;

import static all.Configuration.PROJECT_FILE_PATH;


public class CompilerTest implements Test {
    public static void main(String[] args) {
        new SingleTestClassRunner().run(CompilerTest.class);
    }

    @TestCase
    public void test(){
        new EntityWriter().write(
            PROJECT_FILE_PATH.toString(),
            new TestProjectCreator().create(),
            new Initialization().createMapper()
        );

        new TempStorageSaver().save(
            new CProjectExporter().export(
                new CProjectCreator().create(
                    Objects.requireNonNull(
                        (MgProject) new EntityReader().read(
                            PROJECT_FILE_PATH.toString(),
                            new Initialization().createMapper()
                        )
                    )
                )
            )
        );
    }
}
