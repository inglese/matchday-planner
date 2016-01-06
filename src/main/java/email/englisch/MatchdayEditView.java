package email.englisch;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class MatchdayEditView {

    private final Scene scene;

    private MatchdayEditView() {
        throw new AssertionError("default constructor accidentally invoked from with class MatchdayEditView");
    }
    public MatchdayEditView(LocalDate date) {
        DatePicker dpDate = new DatePicker(date);

        Button btnOk = new Button("_OK");
        btnOk.setDefaultButton(true);
        Button btnCancel = new Button("_Abbrechen");
        btnCancel.setCancelButton(true);
        VBox vbox = new VBox(btnOk, btnCancel);

        BorderPane root = new BorderPane();
        BorderPane.setAlignment(dpDate, Pos.TOP_LEFT);
        root.setCenter(dpDate);
        root.setRight(vbox);

        this.scene = new Scene(root);
    }

    public Scene getScene() {
        return this.scene;
    }
}
