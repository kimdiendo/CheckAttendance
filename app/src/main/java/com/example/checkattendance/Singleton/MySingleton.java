package com.example.checkattendance.Singleton;

public class MySingleton {
    private static MySingleton instance;
    private String variable;
    private int indexing;
    private String position;

    private MySingleton() {
    }

    public static MySingleton getInstance() {
        if (instance == null) {
            instance = new MySingleton();
        }
        return instance;
    }

    private void process_index_user() {
        if (variable.equals("NV01")) {
            indexing = 3;
        } else if (variable.equals("NV02")) {
            indexing = 4;
        } else if (variable.equals("NV04")) {
            indexing = 5;
        } else if (variable.equals("NV05")) {
            indexing = 6;
        }
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getVariable() {
        return variable;
    }

    public int getIndexing() {
        return indexing;
    }

    public void setVariable(String variable) {
        this.variable = variable;
        process_index_user();
    }
}