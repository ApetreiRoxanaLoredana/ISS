package controller;

import domain.Utilizator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.Page;
import service.ServiceException;

import java.io.IOException;

public class LoginController {

    private Page page;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    public LoginController(Page page) {
        this.page = page;
    }

    @FXML
    void clickBtnLogin(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/mainView.fxml"));
            Utilizator utilizator = page.getUtilizatorService().cautaUtilizatorDupaUsernameSiParola(txtUsername.getText(), txtPassword.getText());
            page.setUtilizator(utilizator);

            Page newPage = new Page(utilizator, page.getCarteService(), page.getUtilizatorService(), page.getImprumutService());
            fxmlLoader.setController(new MainController(newPage));

            Pane myPane = fxmlLoader.load();
            Scene myScene = new Scene(myPane);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Main");
            Stage newStage= new Stage();
            newStage.setScene(myScene);
            newStage.show();

            txtUsername.setText("");
            txtPassword.setText("");
        } catch (ServiceException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Autentificare esuata");
            alert.setContentText("Username-ul sau parola sunt gresite");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
