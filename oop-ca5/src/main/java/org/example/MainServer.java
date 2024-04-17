package org.example;

// Run this file to start Server.

/**
 * OOP CA5 Group project:
 * <p>Anastasia McCormac</p>
 * <p>Ben McKeever</p>
 * <p>Kaylon Riordan</p>
 * <p>
 * This program was written in collaboration with use of Xampp for Database access.
 * </p>
 * <p>
 * If necessary, please see attached db.sql file for relevant Database to the functions of this app.
 * </p>
 */
public class MainServer {

    public static void main(String[] args) {

        AppServer appServer = new AppServer();
        appServer.Start();
    }
}