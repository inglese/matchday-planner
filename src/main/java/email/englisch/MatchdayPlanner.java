package email.englisch;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

import java.time.LocalDate;

public class MatchdayPlanner extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Matchday matchday = Matchday.on(LocalDate.now());
        Label label = new Label(matchday.toString());

        StackPane root = new StackPane(label);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Matchday Planner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
