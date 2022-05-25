package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class TCP_Client {
    private String username;
    private ArrayList<String> contacts;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public TCP_Client(String username, ArrayList<String> contacts){
        this.username=username;
        this.contacts=contacts;
    }

    public void askServer() {
        Socket socket = null;
        boolean connected = true;
        String mssg;
        try {
            int serverPort = 49152;
            socket = new Socket("localhost", serverPort);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            while (connected) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Write your message or write 'end_chat' if you want to sop the chat");
                mssg = scanner.nextLine();
                if (mssg.equalsIgnoreCase("end_chat")) {
                    connected=false;
                    out.writeUTF("end_chat");
                } else {
                    mssg= "User: "+username+" says: "+mssg;
                    out.writeUTF(mssg);
                }

            }

        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());} finally {

            if (socket != null) try {
                socket.close();
            } catch (IOException e) {
                System.out.println("close:" + e.getMessage());
            }
        }

    }
}
