package model;

import java.io.*;

public class SerializableModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;

    public SerializableModel(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}