package model;

import data.Data;
import lombok.Getter;

import java.io.Serializable;

@Getter
public enum Food  implements Serializable {
    MEAT(Data.MEAT);

    private final String title;

    Food(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
