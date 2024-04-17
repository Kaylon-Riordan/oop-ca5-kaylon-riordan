package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author: Anastasia McCormac (Unless specified otherwise).
 * <p>
 *  Main server application.
 * </p>
 */
public class AppServer {

    // Class level variable for the port number for the server.
    final int PORT_NUMBER = 8888;

    /**
     * <p>
     *      Server initialization method.
     * </p>
     */
    public void Start() {

        // Client and server sockets used to create connections.
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try {

            // Socket for server to listen on.
            serverSocket = new ServerSocket(PORT_NUMBER);

            System.out.println("Server has started.");

            // Count of clients that have connected.
            int clientCount = 0;

            // Perpetual loop to continue checking for new connection and establish attempts.
            while (true) {

                System.out.println("Server is active and listening on port " + PORT_NUMBER);

                // Program will wait here until a connection is attempted.
                clientSocket = serverSocket.accept();
                clientCount++;

                System.out.println("Client " + clientCount + " connecting on port " + clientSocket.getPort());

                // Create new thread and start it.
                Thread t = new Thread(new ClientHandler(clientSocket, clientCount));
                t.start();
            }
        }

        catch (IOException e) {

            throw new RuntimeException(e);
        }

        // Close resources and shut down server.
        finally {

            try {

                if (clientSocket != null) {

                    clientSocket.close();
                }

                if (serverSocket != null) {

                    serverSocket.close();
                }
            }

            catch (IOException e) {

                throw new RuntimeException(e);
            }
        }
    }
}