package ma.fstt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {
    private static String url ="jdbc:mysql://localhost/gestion_commandes";
    private static String user ="root";
    private static String password ="";


    private static Connection myconnection ;

    private ConnectionJDBC() throws SQLException, ClassNotFoundException {
        System.out.print("Instance jbd ");

        Class.forName("com.mysql.cj.jdbc.Driver");

        myconnection = DriverManager.getConnection(url , user ,password );

    }

    public static Connection getInstance() throws SQLException, ClassNotFoundException {

        if(myconnection == null){

            new ConnectionJDBC();


        }



        return myconnection ;

    }
}
