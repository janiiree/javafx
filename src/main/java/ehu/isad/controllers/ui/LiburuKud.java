package ehu.isad.controllers.ui;

import ehu.isad.Book;
import ehu.isad.Liburuak;
import ehu.isad.controllers.db.LibKud;
import ehu.isad.utils.Sarea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LiburuKud implements Initializable {

    private Liburuak mainApp;
    private Sarea sarea = new Sarea();

    @FXML
    public ComboBox comboLiburuak;

    public void setMainApp(Liburuak main) {
       this.mainApp = main;
   }

   @FXML
    public void onClick(ActionEvent actionEvent) throws IOException {
        Book book = (Book)comboLiburuak.getValue();     //QUITAR COMBOBOX POR DB
        Book liburua = this.sarea.readFromURL(book.getIsbn());
        mainApp.xehetasunakErakutsi(liburua.getDetails().getTitle(), liburua.getDetails().getPublishers()[0], liburua.getDetails().getNumber_of_pages(), liburua.getThumbnail_url());
   }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Book> liburuakList = LibKud.getInstance().lortuLiburuak();
        ObservableList<Book> liburuak = FXCollections.observableArrayList(liburuakList);
        comboLiburuak.setItems(liburuak);
    }

}