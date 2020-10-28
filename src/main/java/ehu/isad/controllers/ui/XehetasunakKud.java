package ehu.isad.controllers.ui;

import ehu.isad.Liburuak;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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

    public void setLabelIzenb(String izenb) { lblIzenburua.setText(izenb); }

    public void setLabelArgitaletxe(String argit) { lblArgtletxea.setText(argit); }

    public void setLabelOrriKop(String orrKop) { lblOrriKop.setText(orrKop); }


}