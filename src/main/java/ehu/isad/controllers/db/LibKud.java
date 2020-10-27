package ehu.isad.controllers.db;

import ehu.isad.Book;
import ehu.isad.Details;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibKud {
    private static final LibKud instance = new LibKud();

    public static LibKud getInstance() { return instance; }

    private LibKud() {
    }

    public List<Book> lortuLiburuak() {
        String query = "select ISBN, izenburua";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<Book> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {
                String isbn = rs.getString("ISBN");
                String izenb = rs.getString("izenburua");
                System.out.println(isbn + ":" + izenb);
                emaitza.add(new Book(isbn, izenb));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return emaitza;
    }

    public boolean liburuaDago(String isbn) throws SQLException {
        String query = "select argitaletxea from liburuak where isbn='" + "'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        try {
            while (rs.next()) {
                String argit = rs.getString("argitaletxea");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rs.next();
    }

    private void libBerriaGorde(String isbn, String izenb, String argit, String orrKop, String irud) {
        String query = "insert into liburuak(ISBN, izenburua, argitaletxea, orriKop, irudia) values(" + isbn + "," + izenb + "," + argit + "," + orrKop + "," + irud + ");";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        dbKudeatzaile.execSQL(query);
    }

    public Book dbkoLiburuaErab(String isbn) {    //COMPROBAR TIPOS --> BOOK???
        String query = "select ISBN, izenburua, argitaletxea, orriKop, irudia from liburuak where ISBN=" + isbn + ";";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        try {
            while(rs.next()) {
                String izenb = rs.getString("izenburua");
                String argit = rs.getString("argitaletxea");
                int orrKop = rs.getInt("orriKop");
                String irud = rs.getString("irudia");

                Book book = new Book(isbn, izenb);
                Details details = new Details();
                details.setTitle(izenb);
                details.setNumber_of_pages(orrKop);
                book.setThumbnail_url(irud);
                book.setDetails(details);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return book;
    }
}
