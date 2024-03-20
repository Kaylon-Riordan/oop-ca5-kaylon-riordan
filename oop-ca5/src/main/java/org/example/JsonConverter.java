package org.example;

import com.google.gson.Gson;
import java.util.List;

public class JsonConverter {
    // Feature 7 - Convert List of Entities to a JSON String
    // e.g. String playersListToJson( List<Player> list )
    /**
     * Author: Kaylon Riordan
     * Method to convert gem list to json string.
     *
     */
    public static String convertListToJsonString(List<Gem> list) {
        return new Gson().toJson(list);
    }
}