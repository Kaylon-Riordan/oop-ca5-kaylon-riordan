package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class DAOTest {

    @Test
    public void login() {
    }

    @Test
    public void getAllGems() {
        DAO dao =  new DAO();

        ArrayList<Gem> expected = new ArrayList<>();
        expected.add(new Gem(1,"Amythest","Quartz",13.21,5,0.3,10,"Purple"));
        expected.add(new Gem(2,"Beryl","Aquamarine",7.34,4,0.5,8,"Blue"));
        expected.add(new Gem(3,"Sapphire","Corundum",5.57,3,0.2,14,"Blue"));
        expected.add(new Gem(4,"Agate","Chalcedony",22.94,4,0.4,19,"Red"));
        expected.add(new Gem(5,"Fluorite","Blue John",35.22,8,0.7,17,"White"));
        expected.add(new Gem(6,"Tourmaline","Rubellite",8.51,7,0.3,3,"Pink"));
        expected.add(new Gem(7,"Apatite","Moroxite",4.39,9,0.4,4,"Green"));
        expected.add(new Gem(8,"Malaia Garnet","Garnet Group",3.7,6,0.1,7,"Red"));
        expected.add(new Gem(9,"Star Saphire","Corundum",16.17,4,0.8,13,"Purple"));
        expected.add(new Gem(10,"Tourmaline","Verdelite",8.51,5,0.4,10,"Green"));

        ArrayList<Gem> actual = dao.getAllGems();

        Assert.assertEquals(expected.size(), actual.size());
        if(expected.size() == actual.size()){
            for(int i = 0; i < expected.size(); i++){
                Assert.assertEquals(expected.get(i).getId(), actual.get(i).getId());
                Assert.assertEquals(expected.get(i).getName(), actual.get(i).getName());
                Assert.assertEquals(expected.get(i).getType(), actual.get(i).getType());
                Assert.assertEquals(expected.get(i).getWeight(), actual.get(i).getWeight(), 0.0000001);
                Assert.assertEquals(expected.get(i).getPrice(), actual.get(i).getPrice(), 0.0000001);
                Assert.assertEquals(expected.get(i).getClarity(), actual.get(i).getClarity(), 0.0000001);
                Assert.assertEquals(expected.get(i).getStock(), actual.get(i).getStock());
                Assert.assertEquals(expected.get(i).getColour(), actual.get(i).getColour());
            }
        }
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