package model;

import data.Data;

public enum Food {
    MEAT(Data.MEAT),
    MOUSE(Data.MOUSE),
    FISH(Data.FISH);

    private final String title;

    Food(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
