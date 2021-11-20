package com.baich.javase.io.day01;

public class generatorTest {
    private int id;
    private String name;
    private float score;

    public String getName() {
        return name;
    }

    public float getScore() {
        return score;
    }

    public int getId() {
        return id;
    }

    public generatorTest(int id, String name, float score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
