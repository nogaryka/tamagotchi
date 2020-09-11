package model;

public enum GrowthPhase {
    EGG("Яйцо"),
    NEWBORN("Новорожднный"),
    YOUNG("Молодой"),
    ADULT("Взрослый"),
    OLD("Старый");

    private String tittle;

    GrowthPhase(String tittle)
    {
        this.tittle = tittle;
    }
}
