package controller;

import controller.dtoFx.ImprumutFx;
import domain.Carte;
import domain.Imprumut;
import domain.StatusCarte;
import domain.StatusImprumut;
import events.CarteChangeEvent;
import events.ChangeEventType;
import events.ImprumutChangeEvent;
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

public class ImprumuturiController implements Initializable, Observer<ImprumutChangeEvent> {
    private Page page;
    private final ObservableList<ImprumutFx> imprumuturi = FXCollections.observableArrayList();

    @FXML
    private Button btnFinish;

    @FXML
    private TableColumn<ImprumutFx, LocalDate> dateColumn;

    @FXML
    private TableColumn<ImprumutFx, Integer> idBookColumn;

    @FXML
    private TableColumn<ImprumutFx, Integer> idBorrowColumn;

    @FXML
    private TableColumn<ImprumutFx, Integer> idUserColumn;

    @FXML
    private TableColumn<ImprumutFx, CheckBox> selectColumn;

    @FXML
    private TableView<ImprumutFx> table;

    @FXML
    private TableColumn<ImprumutFx, String> titleBookColumn;

    @FXML
    private TableColumn<ImprumutFx, String> usernameColumn;


    public ImprumuturiController(Page page) {
        this.page = page;
        page.getImprumutService().addObserver(this);
        init(page.getImprumutService().obtineToateImprumuturileDupaStatus(StatusImprumut.ACCEPTAT));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idBookColumn.setCellValueFactory(new PropertyValueFactory<>("idBook"));
        idBorrowColumn.setCellValueFactory(new PropertyValueFactory<>("idBorrow"));
        idUserColumn.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        titleBookColumn.setCellValueFactory(new PropertyValueFactory<>("titleBook"));
        selectColumn.setCellValueFactory(new PropertyValueFactory<>("selectButton"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        table.setItems(imprumuturi);
        table.setPlaceholder(new Label("No available shows"));
    }

    private void init(Iterable<Imprumut> imprumuturi){
        List<ImprumutFx> newImprumuturiList = new ArrayList<>();
        imprumuturi.forEach(imprumut -> {
            newImprumuturiList.add(new ImprumutFx(imprumut.getId(), imprumut.getUtilizator().getId(),
                    imprumut.getUtilizator().getUsername(), imprumut.getCarte().getId(),
                    imprumut.getCarte().getTitlu(), imprumut.getData()));
        });

        this.imprumuturi.setAll(newImprumuturiList);
    }

    @Override
    public void update(ImprumutChangeEvent imprumutChangeEvent) {
        init(page.getImprumutService().obtineToateImprumuturileDupaStatus(StatusImprumut.ACCEPTAT));
    }


    @FXML
    void clickBtnFinish(ActionEvent event) {
        var imprumuturiFx = table.getItems();
        List<Imprumut> imprumuturiFinalizate = new ArrayList<>();
        List<Carte> cartiImprumutate = new ArrayList<>();
        imprumuturiFx.forEach(imprumutFx -> {
            if (imprumutFx.getSelectButton().isSelected()) {
                var imprumut = page.getImprumutService().obtineImprumutDupaId(imprumutFx.getIdBorrow());
                var carte = imprumut.getCarte();
                carte.setStatusCarte(StatusCarte.LIBERA);
                imprumut.setCarte(carte);
                imprumuturiFinalizate.add(imprumut);
            }
        });
        page.getImprumutService().modificaStatusImprumuturi(imprumuturiFinalizate, StatusImprumut.FINALIZAT);
        page.getCarteService().notifyObservers(new CarteChangeEvent(ChangeEventType.UPDATE, null));
    }
}
