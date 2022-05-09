package controller;

import controller.dtoFx.CarteFx;
import domain.Carte;
import domain.StatusCarte;
import events.CarteChangeEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;
import observer.Observer;
import service.Page;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class CartiController implements Initializable, Observer<CarteChangeEvent> {
    private Page page;

    private final ObservableList<CarteFx> books = FXCollections.observableArrayList();


    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<CarteFx, String> columnAuthor;

    @FXML
    private TableColumn<CarteFx, Integer> columnId;

    @FXML
    private TableColumn<CarteFx, String> columnPublishingHouse;

    @FXML
    private TableColumn<CarteFx, String> columnStatus;

    @FXML
    private TableColumn<CarteFx, String> columnTitle;

    @FXML
    private TableView<CarteFx> tableBooks;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtPublishingHouse;

    @FXML
    private TextField txtTitle;

    public CartiController(Page page) {
        this.page = page;
        init(page.getCarteService().obtineToateCartile());
        page.getCarteService().addObserver(this);
    }

    @FXML
    void clickBtnAdd(ActionEvent event) {
        if(validation()){
            page.getCarteService().adaugaCarte(txtTitle.getText(), txtAuthor.getText(), txtPublishingHouse.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Adaugare cu succes");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Adaugare esuata");
            alert.setContentText("Datele introduse sunt invalide");
            alert.showAndWait();
        }
    }

    private boolean validation() {
        if (Objects.equals(txtTitle.getText(), ""))
            return false;
        if (Objects.equals(txtAuthor.getText(), ""))
            return false;
        if (Objects.equals(txtPublishingHouse.getText(), ""))
            return false;
        return true;
    }

    @FXML
    void clickBtnDelete(ActionEvent event) {
        if (!tableBooks.getSelectionModel().isEmpty()){
            var carte = tableBooks.getSelectionModel().getSelectedItem();
            page.getCarteService().stergeCarte(carte.getId());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Stergere cu succes");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Stergere esuata");
            alert.setContentText("Selecteaza o carte");
            alert.showAndWait();
        }
    }

    @FXML
    void clickBtnUpdate(ActionEvent event) {
        if(validation() && !tableBooks.getSelectionModel().isEmpty()){

            var carte = tableBooks.getSelectionModel().getSelectedItem();
            page.getCarteService().modificaCarte(carte.getId(), txtTitle.getText(), txtAuthor.getText(), txtPublishingHouse.getText(), StatusCarte.valueOf(carte.getStatus()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Modificare cu succes");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Modificare esuata");
            alert.setContentText("Datele introduse sunt invalide sau nu ai selectat nici o carte");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("titlu"));
        columnAuthor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        columnPublishingHouse.setCellValueFactory(new PropertyValueFactory<>("editura"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableBooks.setItems(books);
        tableBooks.setPlaceholder(new Label("No available shows"));

        tableBooks.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                var carte = tableBooks.getSelectionModel().getSelectedItem();
                txtTitle.setText(carte.getTitlu());
                txtAuthor.setText(carte.getAutor());
                txtPublishingHouse.setText(carte.getEditura());
            }
        });

    }

    private void init(Iterable<Carte> carti){
        List<CarteFx> newBooksList = new ArrayList<>();
        carti.forEach(book -> {
            newBooksList.add(new CarteFx(book));
        });

        books.setAll(newBooksList);
    }


    @Override
    public void update(CarteChangeEvent carteChangeEvent) {
        init(page.getCarteService().obtineToateCartile());
    }
}
