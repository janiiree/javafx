package ehu.isad;

import ehu.isad.controllers.ui.LiburuKud;
import ehu.isad.controllers.ui.XehetasunakKud;
import ehu.isad.utils.Sarea;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Liburuak extends Application {

    private Parent liburuUI;
    private Parent xehetasunakUI;

    private Stage stage;

    private Scene libScn;
    private Scene xeheScn;

    private LiburuKud liburuKud;
    private XehetasunakKud xehetasunakKud;

    private Sarea sarea = new Sarea();

    private ImageView img;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        pantailaKargatu();

        stage.setTitle("OpenLibrary APIa aztertzen");
        libScn = new Scene(liburuUI, 750, 500);
        xeheScn = new Scene(xehetasunakUI, 750, 500);
        libHautatuErakutsi();
    }

    private void pantailaKargatu() throws IOException {
        FXMLLoader loaderLiburuHaut = new FXMLLoader(getClass().getResource("/Liburuak.fxml"));
        liburuUI = (Parent) loaderLiburuHaut.load();
        liburuKud = loaderLiburuHaut.getController();
        liburuKud.setMainApp(this);

        FXMLLoader loaderXehet = new FXMLLoader(getClass().getResource("/Xehetasunak.fxml"));
        xehetasunakUI = (Parent) loaderXehet.load();
        xehetasunakKud = loaderXehet.getController();
        xehetasunakKud.setMainApp(this);
    }

    public void libHautatuErakutsi() {
        stage.setScene(libScn);
        stage.show();
    }

    public void xehetasunakErakutsi(String izenb, String argit, String orrKop, String thumbURL) throws IOException {
        xehetasunakKud.setLabelIzenb(izenb);
        xehetasunakKud.setLabelArgitaletxe(argit);
        xehetasunakKud.setLabelOrriKop(orrKop);
        img.setImage(xehetasunakKud.createImage(thumbURL.replace("S","M")));
        stage.setScene(xeheScn);
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}