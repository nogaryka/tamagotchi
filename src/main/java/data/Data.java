package data;

import model.Pet;

import java.util.ArrayList;
import java.util.List;

public final class Data {
    //Enum values
    //For humor
    public final static String GREAT = "Отличное";
    public final static String GOOD = "Хорошее";
    public final static String NORMAL = "Нормальное";
    public final static String BAD = "Плохое";
    public final static String HORRIBLE = "Ужасное";
    public final static String NOT_FOUND= "Уже не важно";
    //For food
    public final static String MEAT= "Мясо";
    //Paths to fxml schemas
    public final static String MAIN_VIEW = "/view/MainView.fxml";
    public final static String CHOOSE_PET_VIEW = "/view/ChoosePetView.fxml";
    public final static String MENU_VIEW = "/view/MenuView.fxml";
    //List pets
    public final static List<Pet> listPets = new ArrayList<Pet>();
    static
    {
        Pet dragor = new Pet("Dragon");
        listPets.add(dragor);
    }
}
