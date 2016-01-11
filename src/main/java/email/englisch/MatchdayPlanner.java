package email.englisch;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;

public class MatchdayPlanner extends Application {

    private Matchday matchday;

    @Override
    public void start(Stage primaryStage) throws Exception {
        matchday = Matchday.on(LocalDate.now());

        Label lblMatchday = new Label(matchday.toString());
        BorderPane.setMargin(lblMatchday, new Insets(5, 5, 5, 5));
        Button btnEdit = new Button("_Bearbeiten");
        BorderPane.setMargin(btnEdit, new Insets(5, 5, 5, 5));
        btnEdit.setOnAction(e -> editMatchday(primaryStage));

        BorderPane root = new BorderPane();
        BorderPane.setAlignment(lblMatchday, Pos.TOP_LEFT);
        root.setCenter(lblMatchday);
        root.setRight(btnEdit);

        Scene scene = new Scene(root);

        primaryStage.setTitle("Matchday Planner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void editMatchday(Stage primaryStage) {
        MatchdayEditController matchdayEditController = new MatchdayEditController(matchday.getDate());
        Stage stage = new Stage();
        stage.setTitle("Spieltag bearbeiten");
        stage.setScene(matchdayEditController.getScene());
        stage.initOwner(primaryStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }
}
