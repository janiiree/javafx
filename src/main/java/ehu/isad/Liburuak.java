package ehu.isad;

import ehu.isad.controllers.LiburuKud;
import ehu.isad.controllers.XehetasunakKud;
import ehu.isad.utils.Sarea;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Liburuak extends Application {

    private Parent liburuUI;
    private Parent xehetasunakUI;

    private Stage stage;

    private LiburuKud liburuKud;
    private XehetasunakKud xehetasunakKud;

    private Sarea sarea = new Sarea();

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        pantailaKargatu();

        stage.setTitle("OpenLibrary APIa aztertzen");
        stage.setScene(new Scene(liburuUI, 450, 275));
        stage.show();
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
        stage.setScene(new Scene(liburuUI, 450, 275));
        stage.show();
    }

    public void xehetasunakErakutsi(String izenb, String argit, String orrKop) {
        xehetasunakKud.setLabelIzenb(izenb);
        xehetasunakKud.setLabelArgitaletxe(argit);
        xehetasunakKud.setLabelOrriKop(orrKop);
        stage.setScene(new Scene(xehetasunakUI, 450, 275));
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}