package email.englisch;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Constructs and administers a dialog for creating a matchday
 */
public class MatchdayEditDialog {

    private final Scene scene;
    private final DatePicker dpDate;
    private Button btnOk;
    private Button btnCancel;

    private MatchdayEditDialog() {
        throw new AssertionError("default constructor accidentally invoked from within class MatchdayEditDialog");
    }

    /**
     * Creates a matchday edit dialog with the specified date as default entry in the date field
     *
     * @param date  the date that is shown in the date field as default, not null
     */
    public MatchdayEditDialog(LocalDate date) {
        Objects.requireNonNull(date, "date must not be null");
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

    /**
     * Returns the scene which contains the controls of the dialog
     *
     * @return the scene which contains the controls of the dialog
     */
    public Scene getScene() {
        return this.scene;
    }

    /**
     * Sets the action of the OK button
     *
     * @param action  the action, which is invoked whenever the button is fired
     */
    public void onOkClicked(EventHandler<ActionEvent> action) {
        this.btnOk.setOnAction(action);
    }

    /**
     * Sets the action of the cancel button
     *
     * @param action  the action, which is invoked whenever the button is fired
     */
    public void onCancelClicked(EventHandler<ActionEvent> action) {
        this.btnCancel.setOnAction(action);
    }

    /**
     * Returns the date that was entered by the user
     *
     * @return the date of the date field
     */
    public LocalDate getDate() {
        return this.dpDate.getValue();
    }
}
