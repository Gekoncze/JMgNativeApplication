package all;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.classdetector.ClassDetector;
import cz.mg.classdetector.ClassWriter;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ListSorter;
import cz.mg.nativeapplication.gui.Initialization;

import java.util.Comparator;

import static all.Configuration.CLASSES_FILE_PATH;
import static all.Configuration.JAR_PATH;


public class Preparation {
    public void prepare(){
        new ClassWriter().write(
            CLASSES_FILE_PATH.toString(),
            sort(new ClassDetector().find(JAR_PATH.toString()))
        );

        new Initialization().init();
    }

    private @Mandatory List<Class> sort(@Mandatory List<Class> classes){
        return ListSorter.sort(classes, Comparator.comparing(Class::getName));
    }
}
