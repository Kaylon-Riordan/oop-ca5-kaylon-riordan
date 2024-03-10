package org.example;

import java.sql.*;
import java.util.ArrayList;

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

    // Singleton pattern to create single instance.
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
                        u.setId(res.getInt("ID"));
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

    // TODO Feature 1 – Get all Entities (assuming Player entities in this example)
    //      e.g. getAllPlayers() - return a List of all the entities and display the returned list.
    public ArrayList<Gem> getAllGems() {

        ArrayList<Gem> gemList = new ArrayList<Gem>();


        return gemList;
    }

    // TODO Feature 2 – Find and Display (a single) Entity by Key
    //      e.g. getPlayerById(id ) – return a single entity (DTO) and display its contents.
    public Gem getGemByID(int id) {

        Gem gemByID = null;

        // Prepared statement to find a gem and if found return the gem as an object.

        // Return the gem you find.
        return gemByID;
    }

    // TODO Feature 3 – Delete an Entity by key
    //      e.g. deletePlayerById(id) – remove specified entity from database
    public int deleteGemByID(int id) throws SQLException {

        Connection connection = null;
        int rowsAffected = 0;
        PreparedStatement preparedStatement = null;

        // Prepared statement to attempt to delete by the given ID, return rows affected

        connection = this.getConnection();

        String query = "DELETE FROM Gemstones WHERE ID = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt( 1, id);

        rowsAffected = preparedStatement.executeUpdate();

        if (preparedStatement != null) {

            preparedStatement.close();
        }

        return rowsAffected;
    }

    // TODO Feature 4 – Insert an Entity
    //      (gather data, instantiate a Player object, pass into DAO method for insertion in DB)
    //      e.g. Player insertPlayer(Player p)
    //      return new entity (Player DTO) that includes the assigned auto-id.
    public int insertGem(Gem gem) {

        int rowsAffected;
        // Prepared Statement to add gem with the given properties.

        return 0;
    }
}
