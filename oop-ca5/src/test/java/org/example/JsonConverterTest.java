package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonConverterTest {

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
        String jsonString = j.convertListToJsonString(gemList);

        Assert.assertEquals(expectedString, jsonString);
    }

    @Test
    public void gemToJsonStringIsValid() {
        JsonConverter j = new JsonConverter();
        Gem testGem = new Gem(1, "Ruby", "Rubelite", 5.00, 5.00, 1.0, 5, "Red");

        String expectedString = "{\"id\":1,\"stock\":5,\"weight\":5.0,\"price\":5.0,\"clarity\":1.0,\"name\":\"Ruby\",\"type\":\"Rubelite\",\"colour\":\"Red\"}";
        String jsonString = j.gemToJsonString(testGem);
        Assert.assertEquals(expectedString, jsonString);
    }

    /**
     * Author: Kaylon Riordan
     *
     */
    @Test
    public void  jsonStringToGemValid() {
        JsonConverter j = new JsonConverter();

        String jString = "{\"id\":1,\"stock\":5,\"weight\":5.0,\"price\":5.0,\"clarity\":1.0,\"name\":\"Ruby\",\"type\":\"Rubelite\",\"colour\":\"Red\"}";
        Gem expected = new Gem(1, "Ruby", "Rubelite", 5.00, 5.00, 1.0, 5, "Red");
        Gem actual = j.jsonStringToGem(jString);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getWeight(), actual.getWeight(), 0);
        Assert.assertEquals(expected.getPrice(), actual.getPrice(), 0);
        Assert.assertEquals(expected.getClarity(), actual.getClarity(), 0);
        Assert.assertEquals(expected.getStock(), actual.getStock());
        Assert.assertEquals(expected.getColour(), actual.getColour());
    }

    @Test
    public void  jsonStringToStringValid() {
        JsonConverter j = new JsonConverter();

        String jString = "[{\"id\":1,\"stock\":5,\"weight\":5.0,\"price\":5.0,\"clarity\":1.0,\"name\":\"Ruby\",\"type\":\"Rubelite\",\"colour\":\"Red\"},{\"id\":1,\"stock\":5,\"weight\":5.0,\"price\":5.0,\"clarity\":1.0,\"name\":\"Sapphire\",\"type\":\"Sapphirite\",\"colour\":\"Blue\"}]";
        Gem testGem1 = new Gem(1, "Ruby", "Rubelite", 5.00, 5.00, 1.0, 5, "Red");
        Gem testGem2 = new Gem(1, "Sapphire", "Sapphirite", 5.00, 5.00, 1.0, 5, "Blue");
        List<Gem> expected = new ArrayList<Gem>(Arrays.asList(testGem1,testGem2));
        List<Gem> actual = j.jsonStringtoList(jString);

        Assert.assertEquals(expected.size(), actual.size());
        if(expected.size() == actual.size()){
            for(int i = 0; i < expected.size(); i++){
                Assert.assertEquals(expected.get(i).getId(), actual.get(i).getId());
                Assert.assertEquals(expected.get(i).getName(), actual.get(i).getName());
                Assert.assertEquals(expected.get(i).getType(), actual.get(i).getType());
                Assert.assertEquals(expected.get(i).getWeight(), actual.get(i).getWeight(), 0);
                Assert.assertEquals(expected.get(i).getPrice(), actual.get(i).getPrice(), 0);
                Assert.assertEquals(expected.get(i).getClarity(), actual.get(i).getClarity(), 0);
                Assert.assertEquals(expected.get(i).getStock(), actual.get(i).getStock());
                Assert.assertEquals(expected.get(i).getColour(), actual.get(i).getColour());
            }
        }
    }

    @Test
    public void userToJsonStringValid() {
        JsonConverter j =  new JsonConverter();

        User user = new User("Bob", "Password123", "Bob", false);
        String expected = "{\"id\":0,\"username\":\"Bob\",\"password\":\"Password123\",\"displayName\":\"Bob\",\"isAdmin\":false}";
        String actual = j.userToJsonString(user);

        Assert.assertEquals(expected, actual);
    }
}