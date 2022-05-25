
package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP_Server {


    public static void main(String args[]) {
        try {
            int serverPort = 49152;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                System.out.println("Waiting for messages...");
                Socket clientSocket = listenSocket.accept();  // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made.
                Connection c = new Connection(clientSocket);
                c.start();
            }
        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        }
    }
}


class Connection extends Thread {
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket clientSocket;

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }


    @Override
    public void run() {
        boolean band = true;
        String sender;
        String receiver;
        //int type;
        String text;
        Message mssg;
        try {

            while (band) {
                // an echo server
                mssg = (Message) in.readObject();
                sender = mssg.getSender();
                //receiver = mssg.getReceiver();
                //type = mssg.getType();
                text = mssg.getMessage();

                if (!text.equalsIgnoreCase("end_chat")) {
                    System.out.println("Message received from: " + sender);
                    System.out.println(text);
                    out.writeObject(mssg);

                } else {
                    band = false;
                }
            }

        } catch (EOFException | ClassNotFoundException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            try {

                clientSocket.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}




