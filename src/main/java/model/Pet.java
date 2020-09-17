package model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import data.Data;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import main.Main;
import utils.PetDeserializer;

import java.io.Serializable;
import java.util.Arrays;

@JsonDeserialize(using = PetDeserializer.class)
public class Pet implements Runnable {
    private Type type;
    private IntegerProperty satiety = new SimpleIntegerProperty(1);
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

    private void setType(String type) {
        Arrays.stream(Type.values()).forEach(x -> {
            if (x.getTitle().equals(type)) {
                this.type = x;
            }
        });
    }

    public IntegerProperty getSatiety() {
        return satiety;
    }

    public void setSatiety(long satiety) {
        if (satiety <= 100) {
            IntegerProperty tempSatiety = new SimpleIntegerProperty(this.satiety.get() + (int) satiety);
            if (tempSatiety.get() > 100) {
                this.satiety.setValue(100);
            } else if (tempSatiety.get() < 0) {
                this.satiety.setValue(0);
            } else {
                this.satiety.setValue(this.satiety.get() + satiety);
            }
        }
        else {
            die();
            this.satiety.setValue(0);
        }
        Humor.getHumor(this.satiety.get(), humor);
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
        final Food[] food = new Food[1];
        Arrays.stream(Food.values()).forEach(x -> {
            if (x.getTitle().equals(foodString)) {
                food[0] = x;
            }
        });
        if (food[0] == this.food) {
            setSatiety(20);
        } else {
            setSatiety(-10);
        }
    }

    public void catabolism() {
        if (this.satiety.get() != 0) {
            setSatiety(-1);
        }
    }

    public void die() {
        setType(Type.CORPSE.getTitle());
        Main.imageProperty.setValue(new Image(Data.PATH_LIST.get(Main.pet.getType())));
    }

    @Override
    public String toString() {
        return type.getTitle();
    }

    @Override
    public void run() {
        Platform.runLater(this::catabolism);
    }
}
