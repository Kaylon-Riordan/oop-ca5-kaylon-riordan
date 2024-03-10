package org.example;

import java.sql.SQLException;

/**
 * OOP CA5 Group project:
 * Anastasia McCormac
 * Ben McKeever
 * Kaylon Riordan
 *
 * This program was written in collaboration with use of Xampp for Database access.
 *
 * If necessary, please see attached db.sql file for relevant Database to the functions of this app.
 */

public class Main {

    public static void main(String[] args) throws SQLException {

        /**
         * Please see attached db.sql for matching database content.
         */

        App app = new App();
        app.Start();
    }
}