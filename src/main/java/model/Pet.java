package model;

import lombok.Data;

@Data
public class Pet {
    private String name;
    private double satiety;
    private Humor humor;
    private Food food;
    private GrowthPhase phase;

    public void beBorn() {

    }

    public void feed() {

    }

    public void grow() {

    }

    public void move() {

    }

    public void changeMood() {

    }
}
