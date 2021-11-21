package all;

import java.nio.file.Path;
import java.nio.file.Paths;


public class Configuration {
    public static final Path TEMP_PATH = Paths.get("/home/me/Temporary");
    public static final Path JAR_PATH = Paths.get(TEMP_PATH.toString(), "artifacts/JMgNativeApplication_jar/JMgNativeApplication.jar");
    public static final Path TESTS_JAR_PATH = Paths.get(TEMP_PATH.toString(), "artifacts/JMgNativeApplicationTests_jar/JMgNativeApplicationTests.jar");
    public static final Path PROJECT_FILE_PATH = Paths.get(TEMP_PATH.toString(), "test/Test.mg");
    public static final Path CLASSES_FILE_PATH = Paths.get("/home/me/Desktop/Dev/Java/JMgNativeApplication/src/cz/mg/nativeapplication/gui/Classes.txt");
}
