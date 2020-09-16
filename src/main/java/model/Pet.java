package model;

import data.Data;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Pet implements Serializable {
    private Type type;
    private IntegerProperty satiety = new SimpleIntegerProperty(50);
    private StringProperty humor = new SimpleStringProperty(Humor.NORMAL.getTitle());
    private Food food;

    public Pet(String type)
    {
        setType(type);
        setFood(this.type);
    }

    public Type getType() {
        return type;
    }

    public void setType(String type) {
        Arrays.stream(Type.values()).forEach(x -> {
            if(x.getTitle().equals(type))
            {
                this.type = x;
            }
        });
    }

   /* public int getSatiety() {
        return satiety;
    }

    public void setSatiety(int satiety) {
        int tempSatiety = this.satiety + satiety;
        if(tempSatiety > 100) {
            this.satiety = 100;
        } else if (tempSatiety < 0) {
            this.satiety = 0;
        } else {
            this.satiety += satiety;
        }
    }*/

    public IntegerProperty getSatiety() {
        return satiety;
    }

    public void setSatiety(int satiety) {
        IntegerProperty tempSatiety = new SimpleIntegerProperty(this.satiety.get() + satiety);
        if(tempSatiety.get() > 100) {
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
            if(x.getTitle().equals(foodString))
            {
                food = x;
            }
        });

        if (food == this.food)
        {
            setSatiety(20);
        }
        else
        {
            setSatiety(-10);
        }
        humor = Humor.getHumor(satiety.get());
    }

    @Override
    public String toString() {
        return type.getTitle();
    }
}
