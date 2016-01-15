package email.englisch;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.time.LocalDate;

public class MatchdayEditController {

    private final MatchdayEditDialog matchdayEditDialog;
    private final Stage stage;

    private MatchdayEditController() {
        throw new AssertionError("default constructor accidentally invoked from within class MatchdayEditController");
    }

    public MatchdayEditController(final Window owner, final LocalDate date) {
        this.matchdayEditDialog = new MatchdayEditDialog(date);
        matchdayEditDialog.onOkClicked((e -> closeMatchdayEditDialog()));
        matchdayEditDialog.onCancelClicked((e -> closeMatchdayEditDialog()));

        this.stage = new Stage();
        stage.setTitle("Neuer Spieltag");
        stage.setScene(matchdayEditDialog.getScene());
        stage.initOwner(owner);
        stage.initModality(Modality.WINDOW_MODAL);
    }

    public void showMatchdayEditDialogAndWait() {
        this.stage.showAndWait();
    }

    private void closeMatchdayEditDialog() {
        this.stage.hide();
    }
}
