package com.example.checkattendance.Singleton;

public class MySingleton {
    private static MySingleton instance;
    private String variable;
    private int indexing;

    private MySingleton() {
    }

    public static MySingleton getInstance() {
        if (instance == null) {
            instance = new MySingleton();
        }
        return instance;
    }

    private void process_index_user() {
        if (variable.equals("NV04")) {
            indexing = 15;
        }
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