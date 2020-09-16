package data;

import model.Food;
import model.Pet;
import model.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    //For type
    public final static String DRAGON = "Дракон";
    public final static String BEAR= "Медведь";
    //Paths to fxml schemas
    public final static String MAIN_VIEW = "/view/MainView.fxml";
    public final static String CHOOSE_PET_VIEW = "/view/ChoosePetView.fxml";
    public final static String MENU_VIEW = "/view/MenuView.fxml";
    // Path to load file
    public final static String PATH_TO_LOAD_FILE = "/loadFiles/savePet.txt";
    // Path to pet images
    public final static String PATH_TO_THE_DRAGON_IMAGE = "/image/dragon.jpg";
    public final static Map<Type, String> PATH_LIST = new HashMap<>();
    static {
        PATH_LIST.put(Type.DRAGON, PATH_TO_THE_DRAGON_IMAGE);
    }
    public final static Map<Type, Food> PET_FOOD_LIST = new HashMap<>();
    static {
        PET_FOOD_LIST.put(Type.DRAGON, Food.MEAT);
    }
}
