package cz.mg.nativeapplication.entities.qt;


public class QtConfig {
    public String name;
    public Operation operation;
    public String value;

    public QtConfig() {
    }

    public QtConfig(String name, Operation operation, String value) {
        this.name = name;
        this.operation = operation;
        this.value = value;
    }

    public enum Operation {
        ADD,
        REMOVE
    }
}
