package model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import data.Data;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import utils.PetDeserializer;

import java.io.Serializable;
import java.util.Arrays;

@JsonDeserialize(using = PetDeserializer.class)
public class Pet implements Runnable, Serializable {
    private Type type;
    private IntegerProperty satiety = new SimpleIntegerProperty(50);
    private StringProperty humor = new SimpleStringProperty(Humor.NORMAL.getTitle());
    private Food food;
    private int catabolismRatePerMinute = 4;

    public Pet(String type) {
        setType(type);
        setFood(this.type);
    }

    public Pet(Type type, IntegerProperty satiety, StringProperty humor, Food food, int catabolismRatePerMinute) {
        this.type = type;
        this.satiety = satiety;
        this.humor = humor;
        this.food = food;
        this.catabolismRatePerMinute = catabolismRatePerMinute;
    }

    public Pet() {
    }

    public Type getType() {
        return type;
    }

    public void setType(String type) {
        Arrays.stream(Type.values()).forEach(x -> {
            if (x.getTitle().equals(type)) {
                this.type = x;
            }
        });
    }

    public IntegerProperty getSatiety() {
        return satiety;
    }

    public void setSatiety(int satiety) {
        IntegerProperty tempSatiety = new SimpleIntegerProperty(this.satiety.get() + satiety);
        if (tempSatiety.get() > 100) {
            this.satiety.setValue(100);
        } else if (tempSatiety.get() < 0) {
            this.satiety.setValue(0);
        } else {
            this.satiety.setValue(this.satiety.get() + satiety);
        }
    }

    public StringProperty getHumor() {
        return humor;
    }

    public int getCatabolismRatePerMinute() {
        return catabolismRatePerMinute;
    }

    public void setHumor(StringProperty humor) {
        this.humor = humor;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Type type) {
        this.food = Data.PET_FOOD_LIST.get(type);
    }

    public void eat(String foodString) {
        final Food foods;
        Arrays.stream(Food.values()).forEach(x -> {
            if (x.getTitle().equals(foodString)) {
                food = x;
            }
        });

        if (food == this.food) {
            setSatiety(20);
        } else {
            setSatiety(-10);
        }
        Humor.getHumor(satiety.get(), humor);
    }

    public void catabolism() {
        if (satiety.get() != 0) {
            setSatiety(-1);
        }
    }

    @Override
    public String toString() {
        return type.getTitle();
    }

    @Override
    public void run() {
        Platform.runLater(() -> catabolism());
    }
}
