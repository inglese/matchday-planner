package email.englisch;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.time.LocalDate;

public class MatchdayEditDialogController {

    private final MatchdayEditDialog matchdayEditDialog;
    private final Stage stage;

    private MatchdayEditDialogController() {
        throw new AssertionError("default constructor accidentally invoked from with class MatchdayEditDialogController");
    }

    public MatchdayEditDialogController(final Window owner, final LocalDate date) {
        this.matchdayEditDialog = new MatchdayEditDialog(date);
        this.stage = new Stage();
        stage.setTitle("Spieltag bearbeiten");
        stage.setScene(matchdayEditDialog.getScene());
        stage.initOwner(owner);
        stage.initModality(Modality.WINDOW_MODAL);
    }

    public void showMatchdayEditDialogAndWait() {
        this.stage.showAndWait();
    }
}
