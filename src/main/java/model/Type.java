package model;

import data.Data;

public enum Type {
    DRAGON(Data.DRAGON),
    BEAR(Data.BEAR),
    CORPSE(Data.CORPSE),
    SNAKE(Data.SNAKE);

    private String title;

    Type(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
