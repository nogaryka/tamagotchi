package model;

import data.Data;
import lombok.Getter;

@Getter
public enum Food {
    MEAT(Data.MEAT);

    private final String title;

    Food(String title) {
        this.title = title;
    }

}
