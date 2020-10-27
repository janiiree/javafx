package ehu.isad.controllers.db;

import ehu.isad.utils.Utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Properties;

public class DBKudeatzaile {

    Connection conn = null;

    private void conOpen() {
        Properties properties = Utils.lortuEzarpenak();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", properties);
            conn.setCatalog(properties.getProperty("dbname"));
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private ResultSet query(Statement s, String query) {
        ResultSet rs = null;

        try {
            s.executeQuery(query);
            rs = s.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    private static final DBKudeatzaile instantzia = new DBKudeatzaile();

    private DBKudeatzaile() { this.conOpen();}

    public static DBKudeatzaile getInstantzia() { return instantzia; }

    public ResultSet execSQL(String query) {
        int count = 0;
        Statement s = null;
        ResultSet rs = null;
        try {
            s = (Statement)conn.createStatement();
            if (query.toLowerCase().indexOf("select") == 0) {
                rs = this.query(s, query);
            } else {
                count = s.executeUpdate(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
