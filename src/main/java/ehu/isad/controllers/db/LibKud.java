package ehu.isad.controllers.db;

import ehu.isad.Book;
import ehu.isad.Details;
import ehu.isad.utils.Utils;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
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
        String query = "select ISBN, izenburua from liburuak";
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
        return rs.next();
    }

    public void libBerriaGorde(Book book) {
        String query = "insert into liburuak(ISBN, izenburua, argitaletxea, orriKop, irudia) values(" + book.getIsbn() + "," + book.getTitle() + "," + book.getDetails().getPublishers()[0] + "," + book.getDetails().getNumber_of_pages() + "," + book.getThumbnail_url() + ");";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        dbKudeatzaile.execSQL(query);
    }

    public Book dbkoLiburuaErab(Book book) {
        String query = "select ISBN, izenburua, argitaletxea, orriKop, irudia from liburuak where ISBN=" + book.getIsbn() + ";";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        try {
            while(rs.next()) {
                String izenb = rs.getString("izenburua");
                String argit = rs.getString("argitaletxea");
                int orrKop = rs.getInt("orriKop");
                String irud = rs.getString("irudia");

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

    public String saveImage(String irudia, String isbn) throws IOException {
        Image img = createImage(irudia);
        File outputFile = new File(Utils.lortuEzarpenak().getProperty("imgPath") + isbn + ".jpg");
        BufferedImage bImage = SwingFXUtils.fromFXImage(img,null);
        try {
            ImageIO.write(bImage,"jpg", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return outputFile.getAbsolutePath();
    }


    public static Image createImage(String url) throws IOException {
        URLConnection conn = new URL(url).openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        try (InputStream stream = conn.getInputStream()) {
            return new Image(stream);
        }
    }

    public Image getImage(String path) {
        BufferedImage bufferedImage = null;
        Image img;
        try {
            File pathFile = new File(path);
            bufferedImage = ImageIO.read(pathFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        img = SwingFXUtils.toFXImage(bufferedImage,null);
        return img;
    }
}
