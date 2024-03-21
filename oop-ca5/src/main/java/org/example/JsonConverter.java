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
    public static String listToJsonString(List<Gem> list) {
        return new Gson().toJson(list);
    }

    // Feature 8 – Convert a single Entity by Key as a JSON String
    //`e.g. String playerToJson( Player p )
    /**
     * Author: Ben Mc Keever
     * Method to convert gem to json string.
     *
     */
    public static String gemToJsonString(Gem g) {
        return new Gson().toJson(g);
    }
}