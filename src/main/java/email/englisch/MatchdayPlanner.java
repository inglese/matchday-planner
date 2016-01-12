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

    @Override
    public void start(Stage primaryStage) throws Exception {
        matchday = Matchday.on(LocalDate.now());

        Label lblMatchday = new Label(matchday.toString());
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
        MatchdayEditDialogController matchdayEditDialogController = new MatchdayEditDialogController(primaryStage, matchday.getDate());
        matchdayEditDialogController.showMatchdayEditDialogAndWait();
        if (matchdayEditDialogController.okWasClicked())
            System.out.println("'OK' was clicked");
        else
            System.out.println("'Abbrechen' was clicked");
    }
}
