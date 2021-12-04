package all;

import cz.mg.classdetector.ClassDetector;
import cz.mg.classdetector.ClassPackager;
import cz.mg.classdetector.PackageBrowser;
import cz.mg.nativeapplication.gui.Initialization;
import cz.mg.test.cli.runners.SingleTestPackageRunner;


public class ExplorerTests {
    public static void main(String[] args) {
        new Initialization().init();
        new SingleTestPackageRunner().run(
            new PackageBrowser().open(
                "cz.mg.nativeapplication.explorer",
                new ClassPackager().pack(
                    new ClassDetector().find(Configuration.TESTS_JAR_PATH.toString())
                )
            )
        );
    }
}
