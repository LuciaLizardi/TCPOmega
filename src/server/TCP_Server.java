package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP_Server {

    public static void main(String args[]) {
        try {
            int serverPort = 49152;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
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

    private DataInputStream in;
    private DataOutputStream out;
    private Socket clientSocket;

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }


    @Override
    public void run() {
        boolean band=true;
        try {
            // an echo server
           while(band) {

               String data = in.readUTF();         // recibo solicitud

               if(!data.equalsIgnoreCase("end_chat")) {
                   //System.out.println("Message received from: " + clientSocket.getRemoteSocketAddress();
                   System.out.println(data);
                   out.writeUTF(data);
                   // envio respuesta
               }
               else{
                   band=false;
               }
           }

        } catch (EOFException e) {
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
