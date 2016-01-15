package email.englisch;

import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class MatchdayEditDialog {

    private final Scene scene;
    private final DatePicker dpDate;
    private Button btnOk;
    private Button btnCancel;

    private MatchdayEditDialog() {
        throw new AssertionError("default constructor accidentally invoked from within class MatchdayEditDialog");
    }
    public MatchdayEditDialog(LocalDate date) {
        dpDate = new DatePicker(date);

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

    public void onOkClicked(EventHandler<ActionEvent> value) {
        this.btnOk.setOnAction(value);
    }

    public void onCancelClicked(EventHandler<ActionEvent> value) {
        this.btnCancel.setOnAction(value);
    }

    public LocalDate getDate() {
        return this.dpDate.getValue();
    }
}
