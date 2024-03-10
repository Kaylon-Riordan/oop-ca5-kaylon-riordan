package org.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main author: Anastasia McCormac
 *
 */
public class App {
        
    App app = this.app;

    // Get Database access operator (DAO), if it doesn't exist it will create it.
    private DAO dao = DAO.getInstance();
    protected User u = null;

    public void Start() throws SQLException {

        Scanner kb = new Scanner(System.in);
        int status = -1;
        String uName = "root";
        String pass = "";

        // Search if the user is valid and create the user object using the given details.
        if (u == null) {

            u = dao.login(uName, pass); // Should we check if this is null?
        }

        // If dao connected properly and user was able to log in send to main menu loop.
        if (u != null && dao != null) {

            do {

                status = app.menu(kb);
            }
            while (status != 0);
        }
    }

    /**
     * Main author: Kaylon Riordan
     * Other contributors: Anastasia McCormac
     *
     */
    private int menu(Scanner kb) throws SQLException {

        int input;

        System.out.print("1: Get a list of all gems.\n" +
                "2: Get a gem by its ID.\n" +
                "3: Delete a gem by its ID.\n" +
                "4: Add a gem to the list.\n" +
                "0: Exit application."
        );

        input = kb.nextInt();
        int id;

        switch (input) {

            case 1: // Get list of all gems and display them to the user.

                displayGem(dao.getAllGems());
                return 1;

            case 2: // Find a single gem bny its ID and display it to the user.

                System.out.print("Enter ID: ");
                id = kb.nextInt();

                displayGem(dao.getGemByID(id));
                return 2;

            case 3: // Delete gem by ID - available only to admins.

                if (u.isAdmin()) {

                    Gem gemDelete;
                    System.out.print("Enter ID to Delete: ");
                    id = kb.nextInt();

                    gemDelete = dao.getGemByID(id);

                    if (gemDelete == null) {

                        System.out.println("Gem does not exist.");
                    }

                    // Confirm with the user if they wish to delete a gem.
                    else {

                        System.out.println("Are you sure you wish to delete this gem? (Y/N):");
                        displayGem(gemDelete);
                        char deleteConfirm = kb.next().trim().toLowerCase().charAt(0);

                        // If user confirms yes, delete the given entry, otherwise return to menu.
                        if (deleteConfirm == 'y') {

                            dao.deleteGemByID(id);
                        }
                    }
                }

                else {

                    System.out.println("You do not have permission to access this command. ");
                }

                return 3;

            case 4: // Add gem case.

                displayGem(addGem(kb));
                return 4;

            case 0: // Exit case.

                System.out.println("Goodbye.");
                return 0;

            default: // Default/invalid case.

                System.out.println("Invalid Input.");
                return -1;
        }
    }

    private Gem addGem(Scanner kb) {

        Gem newGem = new Gem();
        int rowsAffected;

        System.out.print("Gem's Name: ");
        newGem.setName(kb.next());

        System.out.print("Gem's Type: ");
        newGem.setType(kb.next());

        System.out.print("Gem's Weight: ");
        newGem.setWeight(kb.nextDouble());

        System.out.print("Gem's Price: ");
        newGem.setPrice(kb.nextDouble());

        System.out.print("Gem's Clarity: ");
        newGem.setClarity(kb.nextDouble());

        System.out.print("Gem's' Stock: ");
        newGem.setStock(kb.nextInt());

        System.out.print("Gem's Colour: ");
        newGem.setColour(kb.next());

        rowsAffected = dao.insertGem(newGem);

        // Gem was successfully added.
        if (rowsAffected > 0) {

            System.out.println("Gem added successfully");

            // Recreate the gem with its auto-generated ID and return to calling method.
            return dao.getGemByID(newGem.getId());
        }

        // Gem was not found return null.
        else {

            System.out.println("Failed to add gem.");

            return null;
        }
    }

    // Method and overload to display a single Gem in detail, or a list of gems as a table.
    private void displayGem(Gem gem) {

        // TODO display a single gem in detail.
    }

    private void displayGem(ArrayList<Gem> gemList) {

        // TODO Display list of gems as a table.
    }
}