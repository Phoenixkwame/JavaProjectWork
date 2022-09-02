package network2c.hostelallocationsystemfinal;

import network2c.hostelallocationsystemfinal.LoginPage;
import network2c.hostelallocationsystemfinal.RegisterPage;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*This is a Database Access Class used to connect the application
data to the underlining database.
 */


public class DBConnection {
/*
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DBNAME = "pocBase";
    private final String USERNAME = "network2c";
    private final String PASSWORD = "Network12345$$$$$";
    private final String HOST = "localhost";

    private Connection connect = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public DBConnection() {
        try {
            Class.forName(DRIVER);
            connect = DriverManager.getConnection(getUrl());
        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public int save(LoginPage logP) throws SQLException {
        String query = "INSERT INTO loginPage (usernameTextField, passwordPasswordField) values(?,?)";
        ps.setString(1, logP.getUsername());
        ps.setString(2, logP.);


    }


    private String getUrl() {
        return "jdbc:mysql://" + USERNAME + ":" + PASSWORD + "@" + HOST + "/" + DBNAME;
    }

}*/

    public Connection databaseLink;
    public Connection getConnection() {

        String databaseName = "pocBase";
        String databaseUser = "network2c";
        String databasePassword = "Network12345$$$$$";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaseLink;

    }
}
