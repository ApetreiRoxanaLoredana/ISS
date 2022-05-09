package controller;

import controller.dtoFx.CarteFx;
import domain.Carte;
import domain.Imprumut;
import domain.StatusCarte;
import domain.StatusImprumut;
import events.CarteChangeEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import observer.Observer;
import service.Page;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CartiLibereController implements Initializable, Observer<CarteChangeEvent> {
    private Page page;

    private final ObservableList<CarteFx> books = FXCollections.observableArrayList();

    @FXML
    private TableColumn<CarteFx, String> columnAuthor;

    @FXML
    private TableColumn<CarteFx, Integer> columnId;

    @FXML
    private TableColumn<CarteFx, String> columnPublishingHouse;

    @FXML
    private TableColumn<CarteFx, CheckBox> columnSelectButton;

    @FXML
    private TableColumn<CarteFx, String> columnTitle;

    @FXML
    private TableView<CarteFx> tableBooks;

    @FXML
    private TextField txtSearchBook;

    @FXML
    private Button btnSearchBook;

    @FXML
    private Button btnBorrowBooks;

    public CartiLibereController(Page page) {
        this.page = page;
        init(page.getCarteService().obtineToateCartileLibere());
        page.getCarteService().addObserver(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("titlu"));
        columnAuthor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        columnPublishingHouse.setCellValueFactory(new PropertyValueFactory<>("editura"));
        columnSelectButton.setCellValueFactory(new PropertyValueFactory<>("selectButton"));
        tableBooks.setItems(books);
        tableBooks.setPlaceholder(new Label("No available shows"));
    }

    private void init(Iterable<Carte> carti){
        List<CarteFx> newBooksList = new ArrayList<>();
        carti.forEach(book -> {
            newBooksList.add(new CarteFx(book));
        });

        books.setAll(newBooksList);
    }

    @FXML
    void clickBtnSearchBook(ActionEvent event) {
        String text = txtSearchBook.getText();
        init(page.getCarteService().cautaCartiDupaTitluSauAutor(text));
        txtSearchBook.setText("");
    }



    @FXML
    void clickBtnBorrowBooks(ActionEvent event) {
        List<Carte> cartiDeImprumut = new ArrayList<>();
        var cartiFx = tableBooks.getItems();
        for (int i = 0; i < cartiFx.size(); ++i)
        {
            CarteFx carteFx = cartiFx.get(i);
            if (carteFx.getSelectButton().isSelected()) {
                cartiDeImprumut.add(page.getCarteService().obtineCarteDupaId(carteFx.getId()));
            }
        }
        page.getCarteService().modificaStatusCarti(cartiDeImprumut, StatusCarte.REZERVATA);
        List<Imprumut> imprumuturi = new ArrayList<>();
        cartiDeImprumut.forEach(carte -> {
            imprumuturi.add(new Imprumut(StatusImprumut.ASTEPTARE, page.getUtilizator(), carte, LocalDate.now()));
        });
        imprumuturi.forEach(imprumut -> {
            page.getImprumutService().adaugaImprumut(imprumut);
        });

    }

    @Override
    public void update(CarteChangeEvent carteChangeEvent) {
        init(page.getCarteService().obtineToateCartileLibere());
    }
}
