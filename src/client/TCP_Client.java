package client;

import server.Message;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
        String txt = "";
        Message mssg= new Message(username,txt);

        try {
            int serverPort = 49152;
            socket = new Socket("127.0.0.1", serverPort);
            //socket = new Socket("localhost", serverPort);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            while (connected) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Write your message or write 'end_chat' if you want to sop the chat");
                txt = scanner.nextLine();
                if (txt.equalsIgnoreCase("end_chat")) {
                    mssg.setMessage("end_chat");
                    out.writeObject(mssg);
                    connected=false;
                } else {
                    mssg.setMessage(txt);
                    out.writeObject(mssg);
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

/**
 *
 * public class TCPClient {
 *
 *     public static void main(String args[]) {
 *
 *         Socket s = null;
 *
 *         try {
 *             int serverPort = 49152;
 *
 *             s = new Socket("localhost", serverPort);
 *             //s = new Socket("127.0.0.1", serverPort);
 *             ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
 *             Person me = new Person("Yosshua", "Mex", 2000);
 *             //out.writeUTF("Hello");            // UTF is a string encoding
 *             out.writeObject(me);
 *
 *             ObjectInputStream in =  new ObjectInputStream(s.getInputStream());
 *             try{
 *                 me = (Person) in.readObject();
 *                 System.out.println("Received: " + "Name: " + me.getName() + ". Birth place: " + me.getPlace() + ". Year: " + me.getYear());
 *             }catch (Exception ex){
 *                 System.out.println(ex);
 *             }
 *
 *         } catch (UnknownHostException e) {
 *             System.out.println("Sock:" + e.getMessage());
 *         } catch (EOFException e) {
 *             System.out.println("EOF:" + e.getMessage());
 *         } catch (IOException e) {
 *             System.out.println("IO:" + e.getMessage());
 *         } finally {
 *             if (s != null) try {
 *                 s.close();
 *             } catch (IOException e) {
 *                 System.out.println("close:" + e.getMessage());
 *             }
 *         }
 *     }
 * }**/