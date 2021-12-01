package all;

import cz.mg.classdetector.ClassDetector;
import cz.mg.classdetector.ClassPackager;
import cz.mg.classdetector.PackageBrowser;
import cz.mg.test.cli.runners.special.AllTestRunner;


public class AllTests {
    public static void main(String[] args) {
        new AllTestRunner().run(
            new PackageBrowser().open(
                "cz.mg.nativeapplication",
                new ClassPackager().pack(
                    new ClassDetector().find(Configuration.TESTS_JAR_PATH.toString())
                )
            )
        );
    }
}
