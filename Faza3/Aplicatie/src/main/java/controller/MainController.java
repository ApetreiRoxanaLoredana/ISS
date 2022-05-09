package controller;

import domain.StatusUtilizator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import service.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private Page page;

    @FXML
    private Button btnBooks;

    @FXML
    private Button btnBorrowBooks;

    @FXML
    private Button btnBorrows;

    @FXML
    private Button btnRequestsBorrow;

    @FXML
    private VBox vBoxMenu;

    @FXML
    private BorderPane mainBorder;



    public MainController(Page page) {
        this.page = page;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (page.getUtilizator().getStatusUtilizator() == StatusUtilizator.UTILIZATOR) {
            vBoxMenu.setVisible(false);
            vBoxMenu.managedProperty().bind(vBoxMenu.visibleProperty());
        }

        try {
            clickBtnBorrowBooks((ActionEvent) btnBorrowBooks.getOnMouseClicked());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clickBtnBooks(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getClassLoader().getResource("views/cartiView.fxml"));
        fxmlLoader.setController(new CartiController(page));
        Pane view = fxmlLoader.load();
        mainBorder.setCenter(view);
    }

    @FXML
    void clickBtnBorrowBooks(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getClassLoader().getResource("views/cartiLibereView.fxml"));
        fxmlLoader.setController(new CartiLibereController(page));
        Pane view = fxmlLoader.load();
        mainBorder.setCenter(view);
    }

    @FXML
    void clickBtnBorrows(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getClassLoader().getResource("views/imprumuturiView.fxml"));
        fxmlLoader.setController(new ImprumuturiController(page));
        Pane view = fxmlLoader.load();
        mainBorder.setCenter(view);
    }

    @FXML
    void clickBtnRequestBorrow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getClassLoader().getResource("views/cereriDeImprumutView.fxml"));
        fxmlLoader.setController(new CereriDeImprumutController(page));
        Pane view = fxmlLoader.load();
        mainBorder.setCenter(view);
    }

}
