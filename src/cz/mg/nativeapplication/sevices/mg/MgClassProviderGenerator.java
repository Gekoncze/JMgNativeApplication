package cz.mg.nativeapplication.sevices.mg;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;

import java.io.File;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class MgClassProviderGenerator {
    private static final String JAR_PATH = "/home/me/Temporary/artifacts/JMgNativeApplication_jar/JMgNativeApplication.jar";

    public static void main(String[] args) {
        List<Class> classes = new MgClassProviderGenerator().getClasses(JAR_PATH);
        for(Class clazz : classes){
            System.out.println("Class.forName(\"" + clazz.getName() + "\"),");
        }
    }

    private @Mandatory List<Class> getClasses(@Mandatory String path){
        List<Class> classes = new List<>();

        try {
            File jarFile = new File(path);
            ZipInputStream zip = new ZipInputStream(new FileInputStream(jarFile));
            for(ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
                if(!entry.isDirectory()) {
                    if(entry.getName().endsWith(".class")){
                        String classFileName = entry.getName().replace('/', '.');
                        String className = removeClassExtension(classFileName);
                        Class clazz = Class.forName(className);
                        if(isMgEntity(clazz)){
                            classes.addLast(clazz);
                        }
                    }
                }
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        return classes;
    }

    private @Mandatory String removeClassExtension(String classFileName){
        return classFileName.substring(0, classFileName.length() - ".class".length());
    }

    private boolean isMgEntity(@Mandatory Class clazz){
        if(isEntity(clazz)){
            if(clazz.getName().startsWith("cz.mg.nativeapplication")){
                if(clazz.getName().contains("entities.mg")){
                    if(clazz.getSimpleName().startsWith("Mg")){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isEntity(@Mandatory Class clazz){
        return clazz.isAnnotationPresent(Entity.class);
    }
}
