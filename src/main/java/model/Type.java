package model;

public enum Type {
    DRAGON("Дракон");

    private String title;
    Type(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
