package org.example;

import org.junit.Assert;
import org.junit.Test;

public class DAOTest {

    @Test
    public void login() {
    }

    @Test
    public void getAllGems() {
        DAO dao =  new DAO();

        Gem expected = new Gem(5, "Fluorite", "Blue John", 35.22, 8, 0.7, 17, "White");
        Gem actual = dao.getGemByID(5);
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getWeight(), actual.getWeight(), 0.0000001);
        Assert.assertEquals(expected.getPrice(), actual.getPrice(), 0.0000001);
        Assert.assertEquals(expected.getClarity(), actual.getClarity(), 0.0000001);
        Assert.assertEquals(expected.getStock(), actual.getStock());
        Assert.assertEquals(expected.getColour(), actual.getColour());
    }

    @Test
    public void getGemByIDValid() {
        DAO dao =  new DAO();

        Gem expected = new Gem(5, "Fluorite", "Blue John", 35.22, 8, 0.7, 17, "White");
        Gem actual = dao.getGemByID(5);
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getWeight(), actual.getWeight(), 0.0000001);
        Assert.assertEquals(expected.getPrice(), actual.getPrice(), 0.0000001);
        Assert.assertEquals(expected.getClarity(), actual.getClarity(), 0.0000001);
        Assert.assertEquals(expected.getStock(), actual.getStock());
        Assert.assertEquals(expected.getColour(), actual.getColour());
    }

    @Test
    public void getGemByIDInvalid() {
        DAO dao =  new DAO();

        Gem actual = dao.getGemByID(15);
        Assert.assertNull(actual);
    }

    @Test
    public void deleteGemByID() {
    }

    @Test
    public void insertGem() {
    }

    @Test
    public void updateGem() {
    }

    @Test
    public void findGemsUsingFilter() {
    }

    @Test
    public void gemFromResultSet() {
    }
}