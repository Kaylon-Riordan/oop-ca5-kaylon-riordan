package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class JsonConverterTest {

    static App app = new App();

    /**
     * Author: Ben McKeever
     * Check JSON list is valid
     *
     */
    @Test
    public void convertListToJsonStringIsValid() {
        JsonConverter j =  new JsonConverter();
        Gem testGem1 = new Gem(1, "Ruby", "Rubelite", 5.00, 5.00, 1.0, 5, "Red");
        Gem testGem2 = new Gem(1, "Sapphire", "Sapphirite", 5.00, 5.00, 1.0, 5, "Blue");
        List<Gem> gemList = new ArrayList<Gem>(Arrays.asList(testGem1,testGem2));

        String expectedString = "[{\"id\":1,\"stock\":5,\"weight\":5.0,\"price\":5.0,\"clarity\":1.0,\"name\":\"Ruby\",\"type\":\"Rubelite\",\"colour\":\"Red\"},{\"id\":1,\"stock\":5,\"weight\":5.0,\"price\":5.0,\"clarity\":1.0,\"name\":\"Sapphire\",\"type\":\"Sapphirite\",\"colour\":\"Blue\"}]";
        String jsonString = j.listToJsonString(gemList);

        Assert.assertEquals(expectedString, jsonString);
    }

    @Test
    public void gemToJsonStringIsValid() {
        JsonConverter j =  new JsonConverter();
        Gem testGem = new Gem(1, "Ruby", "Rubelite", 5.00, 5.00, 1.0, 5, "Red");

        String expectedString = "{\"id\":1,\"stock\":5,\"weight\":5.0,\"price\":5.0,\"clarity\":1.0,\"name\":\"Ruby\",\"type\":\"Rubelite\",\"colour\":\"Red\"}";
        String jsonString = j.gemToJsonString(testGem);
        Assert.assertEquals(expectedString, jsonString);
    }
}