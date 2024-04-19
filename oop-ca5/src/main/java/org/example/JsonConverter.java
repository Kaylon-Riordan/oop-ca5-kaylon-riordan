package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
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
    // Feature 8 - Convert Entity to a JSON String
    // e.g. String playerToJson( List<Player> list )
    /**
     * Author: Ben Mc Keever
     * Method to convert gem to json string.
     *
     */
    public static String gemToJsonString(Gem g) {
        return new Gson().toJson(g);
    }

    /**
     * Author: Ben Mc Keever
     * Convert json string to gem.
     *
     */
    public static Gem jsonStringToGem(String s) {
        return new Gson().fromJson(s, Gem.class);
    }

    public static List<Gem> jsonStringtoList(String s) {
        // https://stackoverflow.com/questions/18544133/parsing-json-array-into-java-util-list-with-gson
        Type listType = new TypeToken<List<Gem>>() {}.getType();

        return new Gson().fromJson(s, listType);
    }

    /**
     * Author: Anastasia McCormac
     * <p>
     *     Method to convert a user object to JSON.
     * </p>
     * @param u User object to convert.
     * @return String as JSON.
     */
    public static String userToJsonString(User u) {

        return new Gson().toJson(u);
    }
}