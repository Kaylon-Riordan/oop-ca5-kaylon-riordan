package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Author: Anastasia McCormac (Unless specified otherwise).
 * <p>
 *     ClientHandler main program file.
 * </p>
 */
public class ClientHandler implements Runnable {

    BufferedReader in; // Used to read in request protocol.
    PrintWriter out; // Used to send response protocol.
    Socket clientSocket;
    final int clientID; // ID of client connection

    // Get Database access operator (DAO), if it doesn't exist it will create it.
    final private DAO dao = DAO.getInstance();
    protected User u = null;

    // Public Constructor
    public ClientHandler(Socket clientSocket, int clientCount) {

        this.clientSocket = clientSocket;
        this.clientID = clientCount;

        try {

            this.out = new PrintWriter(this.clientSocket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        }

        catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

    // Runnable override, needed to start thread.
    @Override
    public void run() {

        String request;

        // Request string decomposed into array of respective components.
        String[] requestComp;

        // Login protocol: login;<username>;<password>;
        // eg: login;McCormacA;abcd1234
        // Response protocol: <Status>;<JsonString>;
        while (u == null) {

            try {

                request = in.readLine();
                requestComp = request.split(";");

                /*for (String s: requestComp) {
                    System.out.println(s);
                }*/

                if (requestComp[0].equals("login")) {

                    try {

                        u = dao.login(requestComp[1], requestComp[2]);

                        // login Failure return.
                        if (u == null) {

                            out.println("0;LoginFailed;");
                        }

                        // Login success return json object to client.
                        else {

                            out.println("1;" + JsonConverter.userToJsonString(u));
                        }
                    }

                    catch (Exception e) {

                        throw new RuntimeException(e);
                    }
                }

                // Check for exit and quit appropriately.
                else if (requestComp[0].equals("exit")) {

                    System.out.println("Client " + clientID + " has disconnected without logging in.");
                    break;
                }
            }

            catch (IOException e) {

                throw new RuntimeException(e);
            }
        }

        /*
        Request protocol: command;status;data;
        [1]Command: indicates what command has been issued by the client.
        [2]Status: Used to indicate if request has been successful.
        [3]Data: JSON information (or other depending on command needs.)
        Response Protocol: status;data;
        [1]Status: If request has found item.
        [2]Data: Information as JSON String.
        */

        // If user has logged in successfully, run this as main program loop.
        if (u != null) {

            // Return login successful message.
            out.println("1;" + u.getUsername() + ";" + u.isAdmin());

            try {

                while ((request = in.readLine()) != null) {

                    // Decompose response to separate vars.
                    requestComp = request.split(";");

                    // Feature 9 - Display Entity by ID.
                    if (requestComp[0].equals("GemByID")) {

                        int id = Integer.parseInt(requestComp[1]);
                        String json = JsonConverter.gemToJsonString(dao.getGemByID(id));
                        out.println(json);
                    }

                    // Feature 10 - Display all Entities.
                    else if (requestComp[0].equals("allGems")) {

                        String json = JsonConverter.convertListToJsonString(dao.getAllGems());
                        out.println(json);
                    }

                    // Feature 11 - Add an Entity.
                    else if (requestComp[0].equals("AddGem")) {

                        String json = requestComp[1];
                        int newId = dao.getAutoGenId();
                        Gem newGem = JsonConverter.jsonStringToGem(requestComp[1]);

                        if(dao.insertGem(newGem) != 0) { // Success
                            // Return the newly created gem as stored in the database
                            out.println(JsonConverter.gemToJsonString(dao.getGemByID(newId)));
                        } else { // Fail
                            out.println("Insert Failed");
                        }
                    }

                    // Feature 12 - Delete an Entity by ID.
                    else if (requestComp[0].equals("DeleteGemID")) {

                        int id = Integer.parseInt(requestComp[1]);
                        dao.deleteGemByID(id);

                        out.println("Deleted Successfully");
                    }

                    // TODO Feature 13 - Get Images List.
                    else if (requestComp[0].equals("ImageList")) {

                    }

                    // Feature 14 Exit
                    else if (requestComp[0].equals("Exit")) {

                        out.println("Client ID " + this.clientID + " is terminating.");
                        break; // break while loop and finish program.
                    }
                }
            }

            catch (IOException e) {

                throw new RuntimeException(e);
            }

            // Close no longer used resources.
            finally {

                try {

                    this.out.close();
                    this.in.close();
                    this.clientSocket.close();
                }

                catch (IOException e) {

                    throw new RuntimeException(e);
                }
            }
        }

        System.out.println("Client with ID " + clientID + " is terminating.");
    }
}