import domain.DomainController;
import gui.AnagramFrame;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import persistence.PersistenceController;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        DomainController domainController = new DomainController();

        Scene scene = new Scene(new AnagramFrame(domainController));
        stage.setTitle("Anagram Solver");
        stage.setScene(scene);

        stage.setOnShown((WindowEvent t) -> {
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());
        });
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
