package ehu.isad.controllers;

import ehu.isad.Book;
import ehu.isad.Liburuak;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class XehetasunakKud implements Initializable {

    private Liburuak mainApp;

    @FXML
    private Label lblIzenburua;

    @FXML
    private Label lblArgtletxea;

    @FXML
    private Label lblOrriKop;

    @FXML
    private Button btnAtzera;

    @FXML
    private ImageView img;

    public void setMainApp(Liburuak main) {
        this.mainApp = main;
    }

    @FXML
    public void onClick(ActionEvent actionEvent) throws IOException {
        mainApp.libHautatuErakutsi();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void infoErakutsi(Book book) throws IOException {
        lblIzenburua.setText(book.getDetails().getTitle());
        lblOrriKop.setText(book.getDetails().getNumber_of_pages());
        lblArgtletxea.setText(book.getDetails().getPublishers()[0]);
//      img.setImage(createImage(book.getThumbnail_url().replace("S","M")));
    }

/*    private Image createImage(String url) throws IOException {
        URLConnection conn = new URL(url).openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        try (InputStream stream = conn.getInputStream()) {
            return new Image(stream);
        }
        ;
    }
 */

}