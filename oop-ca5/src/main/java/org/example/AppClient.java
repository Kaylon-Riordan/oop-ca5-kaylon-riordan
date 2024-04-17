package org.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Author: Anastasia McCormac (Unless specified otherwise).
 * <p>
 *     Client main program file.
 * </p>
 */
public class AppClient {

    final String HOST_NAME = "localhost"; // DNS/IP of server to connect to.
    final int PORT_NUMBER = 8888; // Port to try to connect through.
    User u = null; // User instance.

    /**
     * Client Initializer
     */
    public void Start() {

        try {

            // create socket to connect to the server
            try (Socket socket = new Socket(HOST_NAME, PORT_NUMBER)) {

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner keyboard = new Scanner(System.in);
                String request;
                String response;

                System.out.println("Client message: The Client is running and has connected to the server");
                System.out.println("Press enter to continue. . .");
                keyboard.nextLine();

                // Dummy user for testing.
                // User u1 = new User("un", "pw", "dp", true );
                // System.out.println(JsonConverter.userToJsonString(u1));

                // Login protocol: login;<username>;<password>;
                // eg: login;Admin;pass1
                // Response protocol: <Status>;<JsonString>;

                // Client login process loop.
                while (true) {

                    // Ask for user log in details.
                    System.out.println("Please enter your username: ('Admin')");
                    String username = keyboard.nextLine();
                    System.out.println("Please enter your password: ('pass1')");
                    String password = keyboard.nextLine();

                    // Send login request to server.
                    System.out.println(("login;" + username + ";" + password + ";"));
                    out.println("login;" + username + ";" + password + ";");
                    // Await response from server.
                    response = in.readLine();

                    // Decompose response to separate vars.
                    String[] responseComp = response.split(";");

                    // Check if log in successful, if not ask user if they want to try again.
                    if (responseComp[0].equals("1")) {

                        // Creates local user object,
                        System.out.println("\nLogin successful, Loading...");
                        u = new Gson().fromJson(responseComp[1], User.class);
                        System.out.println("welcome " + u.getDisplayName());
                    }

                    // Check if login unsuccessful.
                    else if (responseComp[0].equals("0")) {

                        System.out.println("\nInvalid Login, try again? Y/N.");
                        responseComp[0] = keyboard.next();
                    }

                    // If user logs in or if they chose to not try again, break loop and continue program.
                    if (responseComp[0].equals("n") || responseComp[0].equals("1")) {

                        out.println("exit;");
                        break;
                    }
                }

                /*
                Request protocol: command;status;data;
                Command: indicates what command has been issued by the client.
                Status: Used to indicate if request has been successful.
                Data: JSON information (or other depending on command needs.)
                Response Protocol: status;data;
                Status: If request has found item.
                Data: Information as JSON String.
                */

                // Main program loop, only enter if user has logged in successfully.
                if (u != null) {

                    in.readLine(); // Read successful login status message

                    // Perpetual Loop.
                    while (true) {

                        // Display main menu.
                        System.out.print("Main Menu:\n" +
                                "1: Display Gem by ID.\n" +
                                "2: Display all Gems.\n" +
                                "3: Add a gem to the list.\n" +
                                "4: Delete a Gem by ID.\n" +
                                "5: Get Images list.\n" +
                                "0: Exit application.\n"
                        );

                        // Ask user to enter a command (as integer).
                        request = keyboard.next();

                        // Feature 9 - Display Entity by ID.
                        if (Integer.parseInt(request) == 1) {
                            request = "GemByID;";
                            System.out.println("Input ID: ");
                            request += keyboard.next() + ";";

                            out.println(request);
                            Gem gem = JsonConverter.jsonStringToGem(in.readLine());
                            displayGem(gem);
                        }

                        // Feature 10 - Display all Entities.
                        else if (Integer.parseInt(request) == 2) {

                            request = "allGems;";
                            out.println(request);
                            List<Gem> gemList = JsonConverter.jsonStringtoList(in.readLine());
                            displayGem(gemList);
                        }

                        // Feature 11 - Add an Entity.
                        else if (Integer.parseInt(request) == 3) {

                            request = "AddGem;";
                            request += addGemJson(keyboard);

                            out.println(request);

                            response = in.readLine();
                            if(response.contains("Failed")) {
                                System.out.println(response);
                            } else { // Success
                                Gem gem = JsonConverter.jsonStringToGem(in.readLine());
                                displayGem(gem);
                            }
                        }

                        // Feature 12 - Delete an Entity by ID.
                        else if (Integer.parseInt(request) == 4) {

                            request = "AddGem;";
                            System.out.print("Type the ID of the gem you'd like to remove: ");
                            request += keyboard.next();

                            // compile protocol by "DeleteGemID;<status>;<IDtoDelete>; then send to server.
                            //  and await request, processing the return.
                            out.println(request);
                        }

                        // Feature 13 - Get Images List.
                        else if (Integer.parseInt(request) == 5) {


                        }

                        // Feature 14 Exit
                        else if (Integer.parseInt(request) == 0) {

                            out.println("exit;");
                            break; // Break while loop and finish program.
                        }

                        // try to catch invalid input.
                        else {

                            System.out.println("Invalid option, please try again. ");
                        }
                    }

                    System.out.println("Logout Successful.");
                }

                System.out.println("Now exiting program. ");
            }
        }

        catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

    /**
     * Base Author: Kaylon Riordan
     * Updated by Anastasia McCormac
     * Modified to output a JSON by Ben Mc Keever
     * <p>
     *      Method to add a new gem to the database.
     * </p>
     * @param kb Keyboard scanner input.
     * @return If successful returns the gem added.
     */
    private String addGemJson(Scanner kb) {

        Gem newGem = new Gem();

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

        System.out.print("Gem's Stock:\n");
        newGem.setStock(kb.nextInt());

        System.out.print("Gem's Colour:\n");
        newGem.setColour(kb.next());

        return JsonConverter.gemToJsonString(newGem);
    }

    /**
     * Author: Ben Mc Keever
     *
     * Displays all details of a single gem.
     *
     * @param gem The gem to display.
     */
    // Method and overload to display a single Gem in detail, or a list of gems as a table.
    private void displayGem(Gem gem) {
        String template = "%5s%15s%15s%15s%15s%15s%15s%15s";
        String headings = String.format(template, "ID", "Name", "Type", "Weight", "Price", "Clarity", "Stock", "Colour");
        String details = String.format(template, gem.getId(), gem.getName(), gem.getType(), gem.getWeight(), gem.getPrice(), gem.getClarity(), gem.getStock(), gem.getColour());

        System.out.println(headings);
        System.out.println(details);
    }

    private void displayGem(List<Gem> gemList) {
        String template = "%5s%15s%15s%15s%15s%15s%15s%15s";
        String headings = String.format(template, "ID", "Name", "Type", "Weight", "Price", "Clarity", "Stock", "Colour");
        String details;

        System.out.println(headings);
        for (Gem gem : gemList) {
            details = String.format(template, gem.getId(), gem.getName(), gem.getType(), gem.getWeight(), gem.getPrice(), gem.getClarity(), gem.getStock(), gem.getColour());
            System.out.println(details);
        }
    }
}