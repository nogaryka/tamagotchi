package utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Food;
import model.Pet;
import model.Type;

import java.io.IOException;

public class PetDeserializer extends StdDeserializer<Pet> {

    public PetDeserializer() {
        this(null);
    }

    public PetDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Pet deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        Type type = Type.valueOf(node.get("type").asText());
        IntegerProperty satiety = new SimpleIntegerProperty(node.get("satiety").get("value").intValue());
        StringProperty humor = new SimpleStringProperty(node.get("humor").get("value").asText());
        Food food = Food.valueOf(node.get("food").asText());
        int catabolismRatePerMinute = (int) node.get("catabolismRatePerMinute").numberValue();
        return new Pet(type, satiety, humor, food, catabolismRatePerMinute);
    }
}
