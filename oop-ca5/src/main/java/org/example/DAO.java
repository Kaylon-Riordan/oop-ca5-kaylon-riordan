package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * General function Author: Anastasia McCormac
 * Other authors for extended functionality as listed.
 */
public class DAO {

    // Database url
    private String url = "jdbc:mysql://localhost/";
    // Database name
    private String dbname = "OOP_CA5_ABK";
    // Database User
    private String username = "root";
    // Database password
    private String password = "";

    private static DAO instance;
    private DAO () {

    }

    /**
     * Singleton pattern generator to create only ever a single instance of the DAO.
     * @return DAO instance.
     */
    public static DAO getInstance() {

        if (instance == null) {

            instance = new DAO();
        }

        return instance;
    }

    private Connection getConnection() {

        try {

            Connection conn = DriverManager.getConnection(url+dbname, username, password);
            return conn;
        }

        catch (SQLException e) {

            System.out.println("unable to connect to database");
            return null;
        }
    }

    /**
     * Method to try to log in using the linked database table of users.
     *
     * @param username Username to try log in with.
     * @param password Password to try log in with.
     * @return the user if user exists and password matches.
     */
    public User login(String username, String password) {

        User u = null;
        Connection conn = getConnection();

        try {

            if (conn != null) {

                String query = "SELECT * FROM Users WHERE username = ?";

                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, username);

                ResultSet res = stmt.executeQuery();

                while (res.next()) {

                    if (res.getString("password").equals(password)) {

                        u = new User();
                        u.setId(res.getInt("UID"));
                        u.setUsername(res.getString("username"));
                        u.setPassword(res.getString("password"));
                        u.setAdmin(res.getBoolean("isAdmin"));
                    }
                }
            }
        }

        catch (SQLException e) {

            throw new RuntimeException(e);
        }

        return u;
    }

    // Feature 1 – Get all Entities (assuming Player entities in this example)
    //      e.g. getAllPlayers() - return a List of all the entities and display the returned list.
    /**
     * Author: Anastasia McCormac
     * Gets a list of all gems in the database.
     *
     * @return - Arraylist of Gems.
     */
    public ArrayList<Gem> getAllGems()  {

        Connection conn = getConnection();
        ArrayList<Gem> gemList = new ArrayList<Gem>();

        if (conn != null) {

            try {

                String query = "SELECT * FROM `Gemstones`";

                PreparedStatement stmt = conn.prepareStatement(query);

                ResultSet results = stmt.executeQuery();

                while (results.next()) {

                    // Create new instance of gem to add to return list.
                    Gem newGem = new Gem();

                    // Parse corresponding fields from the results set.
                    newGem.setId(results.getInt("ID"));
                    newGem.setName(results.getString("Name"));
                    newGem.setType(results.getString("Type"));
                    newGem.setWeight(results.getDouble("Weight"));
                    newGem.setClarity(results.getDouble("Clarity"));
                    newGem.setPrice(results.getDouble("Price"));
                    newGem.setStock(results.getInt("Stock"));
                    newGem.setColour(results.getString("Colour"));


                    System.out.println("\n" + newGem.toString());

                    // Add the new gem to the return list
                    gemList.add(newGem);
                }
            }

            catch (SQLException e) {

                throw new RuntimeException(e);
            }
        }

        return gemList;
    }

    // Feature 2 – Find and Display (a single) Entity by Key
    // e.g. getPlayerById(id ) – return a single entity (DTO) and display its contents.

    /**
     * Author: Ben McKeever
     *
     * Method to search for a gem by its ID.
     *
     * @param id The ID of a gem to search for.
     * @return if found returns the gem matching the given ID.
     */

    public Gem getGemByID(int id) {

        Connection conn = this.getConnection();
        Gem gemByID = null;

        // Prepared statement to find a gem and if found return the gem as an object.
        if (conn != null) {
            try {

                String query = "SELECT * FROM `Gemstones` WHERE ID = ?";

                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                gemByID = gemFromResultSet(rs);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        // Return the gem you find.
        return gemByID;
    }

    // Feature 3 – Delete an Entity by key
    // e.g. deletePlayerById(id) – remove specified entity from database

    /**
     * Author: Kaylon Riordan
     *
     * Method to delete a gemstone from the database.
     *
     * @param id The ID of the gem to delete.
     * @return Rows affected (0 if unsuccessful)
     */
    public int deleteGemByID(int id) {

        int rowsAffected = 0;
        Connection connection = this.getConnection();

        // Prepared statement to attempt to delete by the given ID, return rows affected
        try {
            String query = "DELETE FROM Gemstones WHERE ID = " + id;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            rowsAffected = preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // 0 for no delete, 1 for entry deleted.
        return rowsAffected;
    }

    // Feature 4 – Insert an Entity
    // (gather data, instantiate a Player object, pass into DAO method for insertion in DB)
    // e.g. Player insertPlayer(Player p)
    // return new entity (Player DTO) that includes the assigned auto-id.
    /**
     * Author: Anastasia McCormac
     * Tries to add a gem to the database.
     *
     * @param gem Gem to add into the database
     * @return Rows affected if 0 gem addition failed.
     */
    public int insertGem(Gem gem) {

        int rowsAffected = 0;

        Connection conn = getConnection();

        if (conn != null) {

            try {

                // Prepared Statement to add gem with the given properties.
                String query = "INSERT INTO Gemstones(Name,Type,Weight,Clarity,Price,Stock,Colour) \n" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?);";

                // Statement initialization.
                PreparedStatement stmt = conn.prepareStatement(query);

                // Statement values setters.
                stmt.setString(1, gem.getName());
                stmt.setString(2, gem.getType());
                stmt.setDouble(3, gem.getWeight());
                stmt.setDouble(4, gem.getClarity());
                stmt.setDouble(5, gem.getPrice());
                stmt.setInt(6, gem.getStock());
                stmt.setString(7, gem.getColour());

                rowsAffected = stmt.executeUpdate();
            }

            catch (SQLException e) {

                throw new RuntimeException(e);
            }
        }

        return rowsAffected;
    }

    // Feature 5 – Update an existing Entity by ID
    // e.g. Player updatePlayer(int id, Player p) – executes specified updates.
    /**
     * Author: Kaylon Riordan
     * Method to update a database entry.
     *
     */
    public Gem updateGem(int id, Gem gem) {

        Connection connection = this.getConnection();

        // use new gem object to update members for the given ID.

        try {
            String query = "UPDATE gemstones SET Name = ? " +
                        ", Type = ? " + ", Weight = ? " +
                        ", Clarity = ? " + ", Price = ? " +
                        ", Stock = ? " + ", Colour = ? " + " WHERE ID = " + id;

            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, gem.getName());
            stmt.setString(2, gem.getType());
            stmt.setDouble(3, gem.getWeight());
            stmt.setDouble(4, gem.getClarity());
            stmt.setDouble(5, gem.getPrice());
            stmt.setInt(6, gem.getStock());
            stmt.setString(7, gem.getColour());

            stmt.executeUpdate();
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return getGemByID(id);
    }

    /**
     * Author: Ben McKeever
     *
     */
    public Gem gemFromResultSet(ResultSet rs) throws SQLException {
        Gem newGem = null;

        if (rs.next()) {
            // Create new instance of gem to add to return list.
            newGem = new Gem(
                    rs.getInt("ID"),
                    rs.getString("Name"),
                    rs.getString("Type"),
                    rs.getDouble("Weight"),
                    rs.getDouble("Clarity"),
                    rs.getDouble("Price"),
                    rs.getInt("Stock"),
                    rs.getString("Colour")
            );
        }

        return newGem;
    }
}
