import controller.LoginController;
import domain.Carte;
import domain.Imprumut;
import domain.StatusImprumut;
import domain.Utilizator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import repository.CarteRepository;
import repository.ImprumutRepository;
import repository.UtilizatorRepository;
import service.CarteService;
import service.ImprumutService;
import service.Page;
import service.UtilizatorService;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        try {
            initialize();


            CarteRepository carteRepository = new CarteRepository(sessionFactory);
            UtilizatorRepository utilizatorRepository = new UtilizatorRepository(sessionFactory);
            ImprumutRepository imprumutRepository = new ImprumutRepository(sessionFactory);
            CarteService carteService = new CarteService(carteRepository);
            UtilizatorService utilizatorService = new UtilizatorService(utilizatorRepository);
            ImprumutService imprumutService = new ImprumutService(imprumutRepository);
            Page page = new Page(null, carteService, utilizatorService, imprumutService);

            primaryStage.setTitle("Login");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("views/loginView.fxml"));

            LoginController loginController = new LoginController(page);
            loader.setController(loginController);

            Pane myPane = loader.load();


            Scene myScene = new Scene(myPane);
            primaryStage.setScene(myScene);
            primaryStage.show();
        }catch (Exception ex){
            System.err.println(ex);
        }finally {
            //close();
        }
    }

    static SessionFactory sessionFactory;
    static void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Exception "+e);
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    static void close(){
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }
}
