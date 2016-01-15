package email.englisch;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class MatchdayPlanner extends Application {

    private Matchday matchday;
    private ListView<Matchday> listView = new ListView<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        matchday = Matchday.on(LocalDate.now());

        Button btnNew = new Button("_Neu");
        btnNew.setOnAction(e -> createMatchday(primaryStage));
        Button btnDelete = new Button("_Löschen");
        btnDelete.setOnAction(e -> deleteMatchday(primaryStage));
        VBox buttonBox = new VBox(btnNew, btnDelete);

        listView.getItems().addAll(
                Matchday.on(LocalDate.now().minusDays(1)),
                Matchday.on(LocalDate.now()),
                Matchday.on(LocalDate.now().plusDays(1))
        );
        listView.setCellFactory((param) -> {
            return new MatchdayCell();
        });

        BorderPane root = new BorderPane();
        BorderPane.setAlignment(listView, Pos.TOP_LEFT);
        root.setCenter(listView);
        root.setRight(buttonBox);

        Scene scene = new Scene(root);

        primaryStage.setTitle("Matchday Planner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static class MatchdayCell extends ListCell<Matchday> {
        @Override
        protected void updateItem(Matchday item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null & !empty)
                setText(item.getDate().toString());
        }
    }

    private void createMatchday(Stage primaryStage) {

    }

    private void deleteMatchday(Stage primaryStage) {

    }
}
