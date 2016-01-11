package email.englisch;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class MatchdayEditDialog {

    private final Scene scene;
    private Button btnOk;
    private Button btnCancel;

    private MatchdayEditDialog() {
        throw new AssertionError("default constructor accidentally invoked from with class MatchdayEditDialog");
    }
    public MatchdayEditDialog(LocalDate date) {
        DatePicker dpDate = new DatePicker(date);

        this.btnOk = new Button("_OK");
        this.btnOk.setDefaultButton(true);
        this.btnCancel = new Button("_Abbrechen");
        this.btnCancel.setCancelButton(true);
        VBox vbox = new VBox(this.btnOk, this.btnCancel);

        BorderPane root = new BorderPane();
        BorderPane.setAlignment(dpDate, Pos.TOP_LEFT);
        root.setCenter(dpDate);
        root.setRight(vbox);

        this.scene = new Scene(root);
    }

    public Scene getScene() {
        return this.scene;
    }

    public Button getBtnOk() {
        return this.btnOk;
    }

    public Button getBtnCancel() {
        return this.btnCancel;
    }
}
