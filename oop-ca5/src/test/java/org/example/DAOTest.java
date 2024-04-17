package org.example;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.Comparator;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DAOTest {

    /**
     * Author: Kaylon Riordan
     * Test that the method accurately returns the original list of gems before changes
     *
     */
    @Test
    @Order(1)
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

    /**
     * Author: Kaylon Riordan
     * Test that the method accurately returns gem 5
     *
     */
    @Test
    @Order(2)
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

    /**
     * Author: Kaylon Riordan
     * Test that the method doesn't return anything when given an invalid input
     *
     */
    @Test
    @Order(3)
    public void getGemByIDInvalid() {
        DAO dao =  new DAO();

        Gem actual = dao.getGemByID(15);
        Assert.assertNull(actual);
    }

    /**
     * Author: Kaylon Riordan
     * Test that the delete method affects 1 row in the table
     *
     */
    @Test
    @Order(4)
    public void deleteGemByIDValid() {
        DAO dao =  new DAO();

        int expected = 1;
        int actual = dao.deleteGemByID(10);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Author: Kaylon Riordan
     * Test that the delete method doesn't affect any rows in the table when given an invalid ID
     *
     */
    @Test
    @Order(5)
    public void deleteGemByIDInvalid() {
        DAO dao =  new DAO();

        int expected = 0;
        int actual = dao.deleteGemByID(100);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Author: Kaylon Riordan
     * Test that the insert method affects 1 row in the table
     *
     */
    @Test
    @Order(6)
    public void insertGem() {
        DAO dao =  new DAO();
        Gem gem = new Gem(11,"Kryptonite","SpaceRock?",14.62,12,0.45,1,"Green");

        int expected = 1;
        int actual = dao.insertGem(gem);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Author: Kaylon Riordan
     * Test that the update method returns the same gem its inputted, showing it was successfully updated
     *
     */
    @Test
    @Order(7)
    public void updateGem() {
        DAO dao =  new DAO();

        Gem expected = new Gem(1,"Amythest","Quartz",13.21,15,0.3,3,"Purple");
        Gem actual = dao.updateGem(1, expected);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getWeight(), actual.getWeight(), 0.0000001);
        Assert.assertEquals(expected.getPrice(), actual.getPrice(), 0.0000001);
        Assert.assertEquals(expected.getClarity(), actual.getClarity(), 0.0000001);
        Assert.assertEquals(expected.getStock(), actual.getStock());
        Assert.assertEquals(expected.getColour(), actual.getColour());
    }

    /**
     * Author: Kaylon Riordan
     * Test that filter returns the correct list of gems
     *
     */
    @Test
    @Order(8)
    public void findGemsUsingFilterValid() {
        DAO dao =  new DAO();

        ArrayList<Gem> expected = new ArrayList<>();
        expected.add(new Gem(2,"Beryl","Aquamarine",7.34,4,0.5,8,"Blue"));
        expected.add(new Gem(5,"Fluorite","Blue John",35.22,8,0.7,17,"White"));
        expected.add(new Gem(9,"Star Saphire","Corundum",16.17,4,0.8,13,"Purple"));

        Gem filterGem = new Gem();
        filterGem.setClarity(0.5);

        ArrayList<Gem> actual = dao.findGemsUsingFilter(Comparator.comparing(Gem::getClarity), filterGem);

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

    /**
     * Author: Kaylon Riordan
     * Test that the filter returns nothing when the input is too high
     *
     */
    @Test
    @Order(9)
    public void findGemsUsingFilterInvalid() {
        DAO dao =  new DAO();

        Gem filterGem = new Gem();
        filterGem.setClarity(0.9);

        ArrayList<Gem> actual = dao.findGemsUsingFilter(Comparator.comparing(Gem::getClarity), filterGem);

        Assert.assertEquals(actual.size(), 0);
    }
}