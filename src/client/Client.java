


package client;

import org.apache.activemq.ActiveMQConnection;

import java.io.Serializable;
import java.util.ArrayList;

public class Client  extends Thread implements Serializable {

    private String username;
    private ArrayList<String> contacts;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Client(String username, ArrayList<String> contacts){
        this.username=username;
        this.contacts=contacts;
    }


    public void run(){
        try{
            TCP_Client client = new TCP_Client(username,contacts);
            client.askServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
