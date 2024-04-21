package org.example;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

                // Set scope of list of ImageFiles
                List<String> imageFiles = null;
                List<String> fileNames = new ArrayList<>();

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

                        if (dao.insertGem(newGem) != 0) { // Success
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

                    // Feature 13 - Get Images List.
                    else if (requestComp[0].equals("ImageList")) {
                        // https://stackoverflow.com/questions/14830313/retrieving-a-list-from-a-java-util-stream-stream-in-java-8
                        imageFiles = Files.list(Paths.get("images/")) // Get all the files and directories in the inputted directory
                                // Run toString on each entry
                                .map(Object::toString)
                                // Filter the paths for image files only
                                .filter(s -> s.endsWith(".jpg") || s.endsWith(".jpeg") || s.endsWith(".png"))
                                // Convert into a list
                                .toList();

                        // Reset fileNames
                        fileNames.clear();

                        // Populate fileNames by removing prefix "images\" from each string
                        imageFiles.forEach(s -> fileNames.add(s.substring(7)));

                        out.println(JsonConverter.stringListtoJsonString(fileNames));
                    }

                    // Feature 13 continued.
                    else if (requestComp[0].equals("ImageDownload")) {
                        String fileName = requestComp[1];
                        int index = fileNames.indexOf(fileName);

                        System.out.println(fileNames);
                        System.out.println(fileName);
                        System.out.println(index);

                        if(index == -1) { // Not found
                            out.println(fileName + " not found.");
                        } else {
                            out.println(fileName + " found.");
                            // Get file directory from imageFiles
                            sendImage(clientSocket, imageFiles.get(index));
                        }
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

    // Initialise a data output stream and send a file.
    //https://github.com/logued/oop-client-server-socket-image/blob/master/src/main/java/org/example/Client_Main.java
    private void sendImage(Socket s, String path) throws IOException {
        DataOutputStream dataOutput = new DataOutputStream((s.getOutputStream()));
        int bytes = 0; // Chunks of 8 bits

        // Open the file
        File image = new File(path);
        FileInputStream fInput = new FileInputStream(image);

        // Send the length of the image to the client
        dataOutput.writeLong(image.length());

        // Create a buffer of 4 kilobytes. One kilobyte is 1024 bytes.
        byte[] buffer = new byte[4 * 1024];

        System.out.println("Entering");
        // Read in the file in chunks of 4 kilobytes until the buffer is full or reached EOF
        while ((bytes = fInput.read(buffer)) != -1) {
            // Send buffer contents along with number of bytes
            dataOutput.write(buffer, 0, bytes);
            dataOutput.flush();
        }
        System.out.println("Leaving");

        // Complete, now close the file
        fInput.close();
    }
}