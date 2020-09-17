package model;

import data.Data;

import java.io.Serializable;

public enum Type {
    DRAGON(Data.DRAGON),
    BEAR(Data.BEAR),
    CORPSE(Data.CORPSE);

    private String title;
    Type(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
