package ehu.isad.controllers;

import ehu.isad.Book;
import ehu.isad.Liburuak;
import ehu.isad.utils.Sarea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LiburuKud implements Initializable {

    private Liburuak mainApp;
    private Sarea sarea = new Sarea();

    @FXML
    public ComboBox comboLiburuak;

    @FXML
    private Label label;

    @FXML
    private Button btnIkusi;

    public void setMainApp(Liburuak main) {
       this.mainApp = main;
   }

   @FXML
    public void onClick(ActionEvent actionEvent) throws IOException {
        mainApp.xehetasunakErakutsi();
   }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Book> books = FXCollections.observableArrayList();
        books.addAll(
                new Book("9781491920497", "Blockchain: Blueprint for a New Economy"),
                new Book("1491910399", "R for Data Science"),
                new Book("1491946008", "Fluent Python"),
                new Book("1491978236", "Natural Language Processing with PyTorch"),
                new Book("9781491906187", "Data Algorithms")
        );

        comboLiburuak.setItems(books);
        comboLiburuak.getSelectionModel().selectFirst();
        comboLiburuak.setEditable(false);

        comboLiburuak.setOnAction( e -> {
            Book book = (Book)comboLiburuak.getValue();
            try {
                Book liburua = sarea.readFromURL(book.getIsbn());
                this.label.setText( liburua.getDetails().getTitle() + "\n" +
                        liburua.getDetails().getNumber_of_pages() + "\n" +
                        liburua.getDetails().getPublishers()[0] );
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        comboLiburuak.setConverter(new StringConverter<Book>() {
            @Override
            public String toString(Book book) {
                if (book == null) { return ""; }
                return book.getTitle();
            }

            @Override
            public Book fromString(String string) {
                return null;
            }
        });
    }
}