package all;

import cz.mg.classdetector.ClassDetector;
import cz.mg.classdetector.ClassPackager;
import cz.mg.classdetector.PackageBrowser;
import cz.mg.test.cli.runners.SingleTestPackageRunner;


public class ExplorerTests {
    public static void main(String[] args) {
        new Preparation().prepare();
        new SingleTestPackageRunner().run(
            new PackageBrowser().open(
                "cz.mg.entity.explorer",
                new ClassPackager().pack(
                    new ClassDetector().find(Configuration.TESTS_JAR_PATH.toString())
                )
            )
        );
    }
}
