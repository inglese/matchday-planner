package email.englisch;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.time.LocalDate;

public class MatchdayPlanner extends Application {

    private Matchday matchday;
    private Label lblMatchday = new Label();

    @Override
    public void start(Stage primaryStage) throws Exception {
        matchday = Matchday.on(LocalDate.now());

        lblMatchday.setText(matchday.toString());
        Button btnEdit = new Button("_Bearbeiten");
        BorderPane.setMargin(lblMatchday, new Insets(5, 5, 5, 5));
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
        MatchdayEditController matchdayEditController = new MatchdayEditController(primaryStage, matchday);
        matchdayEditController.showMatchdayEditDialogAndWait();
        lblMatchday.setText(matchday.toString());
    }
}
