import client.Client;

import java.util.ArrayList;

public class Turbo_Message {
    public static void main(String[] args) {
        ArrayList<String> contacts= new ArrayList<String>();
        Client c= new Client("usuario1",contacts);
        c.start();
    }
}
