import client.Client;

import java.sql.*;
import java.util.ArrayList;

public class Turbo_Message {

    private ArrayList<String> users;
    private ArrayList<Client> _users;

    public Turbo_Message() {
        this.users=new ArrayList<String>();
        this._users=new ArrayList<Client>();
    }

    /**
    public void run() throws ClassNotFoundException, SQLException {
        //Login Page
        String sql="SELECT * FROM turbo_message";
        String URL = "jdbc:mysql://127.0.0.1:3306/omega_tmssg?useSSL=false";
        final String USERNAME="root";
        final String PASSWORD ="Manzana12345!";

        Connection conn=null;
        Statement stmt=null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loadUsers(conn,stmt,sql);
        System.out.println("Users loaded");
    }**/

    public void loadUsers(Connection conn, Statement stmt, String sql) throws SQLException {
        stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        String user;
        while (rs.next()){
            user= rs.getString(1);
            users.add(user);
            //xx   System.out.println(user);
        }


    }
    /**public Connection connection(){
        String sql="SELECT * FROM turbo_message";
        String URL = "jdbc:mysql://127.0.0.1:3306/omega_tmssg?useSSL=false";
        final String USERNAME="root";
        final String PASSWORD ="Manzana12345!";

        Connection conn=null;
        Statement stmt=null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("Connected to database");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }**/

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Turbo_Message tb= new Turbo_Message();
        ArrayList<String> al= new ArrayList<String>();
        Client c= new Client("lucy",al);
        c.run();
        //tb.run();
    }
}
