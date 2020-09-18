package model;

import data.Data;
import javafx.beans.property.StringProperty;

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

    public String getTitle() {
        return title;
    }

    public static void getHumor(double satiety, StringProperty humor) {
        switch ((int) Math.ceil(satiety / 20)) {
            case 5:
                humor.setValue(Humor.GREAT.getTitle());
                break;
            case 4:
                humor.setValue(Humor.GOOD.getTitle());
                break;
            case 3:
                humor.setValue(Humor.NORMAL.getTitle());
                break;
            case 2:
                humor.setValue(Humor.BAD.getTitle());
                break;
            case 1:
                humor.setValue(Humor.HORRIBLE.getTitle());
                break;
            default:
                humor.setValue(Humor.NOT_FOUND.getTitle());
        }
        ;
    }
}
