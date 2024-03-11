package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Author: Anastasia McCormac
 * Main app class
 */
public class App {

    // Get Database access operator (DAO), if it doesn't exist it will create it.
    private DAO dao = DAO.getInstance();
    protected User u = null;

    /**
     * Author: Anastasia McCormac
     * program initializer.
     */
    public void Start() {

        Scanner kb = new Scanner(System.in);
        int status = -1;
        String uName = "McCormacA";
        String pass = "abcd1234";

        // Search if the user is valid and create the user object using the given details.
        if (u == null) {

            u = dao.login(uName, pass); // Should we check if this is null?
        }

        // If dao connected properly and user was able to log in send to main menu loop.
        if (u != null && dao != null) {

            do {

                status = menu(kb);
            }
            while (status != 0);
        }
    }

    /**
     * Author: Kaylon Riordan
     * Updated and tidied by Anastasia McCormac
     * @param kb - keyboard scanner input.
     * @return return status (0 to exit program).
     */
    private int menu(Scanner kb) {

        int input;

        System.out.print("1: Get a list of all gems.\n" +
                "2: Get a gem by its ID.\n" +
                "3: Delete a gem by its ID.\n" +
                "4: Add a gem to the list.\n" +
                "0: Exit application.\n"
        );

        input = kb.nextInt();
        int id;

        switch (input) {

            case 1: // Get list of all gems and display them to the user.

                displayGem(dao.getAllGems());
                return 1;

            case 2: // Find a single gem bny its ID and display it to the user.

                System.out.print("Enter ID:\n");
                id = kb.nextInt();

                displayGem(dao.getGemByID(id));
                return 2;

            case 3: // Delete gem by ID - available only to admins.

                if (u.isAdmin()) {

                    Gem gemDelete;
                    System.out.print("Enter ID to Delete:\n");
                    id = kb.nextInt();

                    gemDelete = dao.getGemByID(id);

                    if (gemDelete == null) {

                        System.out.println("Gem does not exist.");
                    }

                    // Confirm with the user if they wish to delete a gem.
                    else {

                        System.out.println("Are you sure you wish to delete this gem? (Y/N):\n");
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

                System.out.println("\n\nGoodbye.");
                return 0;

            default: // Default/invalid case.

                System.out.println("Invalid Input.");
                return -1;
        }
    }

    /**
     * Base Author: Kaylon Riordan
     * Updated by Anastasia McCormac
     *
     * Method to add a new gem to the database.
     *
     * @param kb Keyboard scanner input.
     * @return If successful returns the gem added.
     */
    private Gem addGem(Scanner kb) {

        Gem newGem = new Gem();
        int rowsAffected;

        System.out.print("Gem's Name:\n");
        newGem.setName(kb.next());

        System.out.print("Gem's Type:\n");
        newGem.setType(kb.next());

        System.out.print("Gem's Weight:\n");
        newGem.setWeight(kb.nextDouble());

        System.out.print("Gem's Price:\n");
        newGem.setPrice(kb.nextDouble());

        System.out.print("Gem's Clarity:\n");
        newGem.setClarity(kb.nextDouble());

        System.out.print("Gem's' Stock:\n");
        newGem.setStock(kb.nextInt());

        System.out.print("Gem's Colour:\n");
        newGem.setColour(kb.next());

        rowsAffected = dao.insertGem(newGem);

        // Gem was successfully added.
        if (rowsAffected > 0) {

            System.out.println("\nGem added successfully");

            // Recreate the gem with its auto-generated ID and return to calling method.
            return dao.getGemByID(newGem.getId());
        }

        // Gem was not found return null.
        else {

            System.out.println("Failed to add gem.");

            return null;
        }
    }

    /**
     * Author: Anastasia McCormac
     *
     * Displays all details of a single gem.
     *
     * @param gem The gem to display.
     */
    // Method and overload to display a single Gem in detail, or a list of gems as a table.
    protected void displayGem(Gem gem) {

        // TODO display a single gem in detail.
        System.out.printf("%10s | %-15d ", "ID:", gem.getId());
        System.out.printf("%10s | %-15s ", "Name:", gem.getName());
        System.out.printf("%10s | %-15s ", "Type:", gem.getType());
        System.out.printf("%10s | %-15f ", "Weight:", gem.getWeight());
        System.out.printf("%10s | %-15f ", "Clarity:", gem.getClarity());
        System.out.printf("%10s | %-15f ", "Price:", gem.getPrice());
        System.out.printf("%10s | %-15d ", "Stock:", gem.getStock());
        System.out.printf("%10s | %-15s ", "Colour:", gem.getColour());

    }

    /**
     * Author: Anastasia McCormac
     *
     * Method to display a table of all gems with some component details.
     *
     * @param gemList ArrayList of gems to display.
     */
    protected void displayGem(ArrayList<Gem> gemList) {
        // TODO Display list of gems as a table.
        Iterator<Gem> iter = gemList.iterator();
        System.out.printf("\n| %-4s | %-15s | %-6s | %-6s | %-15s |\n",
                "ID", "Name", "Price", "Stock", "Colour");

        while (iter.hasNext()) {

            Gem gem = iter.next();

            System.out.printf("| %-4d | %-15s | %-6.2f | %-6d | %-15s |\n",
                    gem.getId(), gem.getName(), gem.getPrice(), gem.getStock(), gem.getColour());
        }
    }
}