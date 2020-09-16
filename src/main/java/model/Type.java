package model;

import data.Data;

import java.io.Serializable;

public enum Type implements Serializable {
    DRAGON(Data.DRAGON),
    BEAR(Data.BEAR);

    private String title;
    Type(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
