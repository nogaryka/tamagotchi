package model;

import data.Data;
import lombok.Getter;

@Getter
public enum Humor {
    GREAT(Data.GREAT),
    GOOD(Data.GOOD),
    NORMAL(Data.NORMAL),
    BAD(Data.BAD),
    HORRIBLE(Data.HORRIBLE),
    NOT_FOUND(Data.NOT_FOUND);

    private final String title;

    Humor(String title) {
        this.title = title;
    }

    public static Humor getHumor(double satiety) {
        Humor humor;
        switch ((int) Math.ceil(satiety / 10))
        {
            case 5: humor = Humor.GREAT;
            case 4: humor = Humor.GOOD;
            case 3: humor = Humor.NORMAL;
            case 2: humor = Humor.BAD;
            case 1: humor = Humor.HORRIBLE;
            default: humor = Humor.NOT_FOUND;
        };
        return humor;
    }
}
