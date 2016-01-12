package email.englisch;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.time.LocalDate;

public class MatchdayEditDialogController {

    private final MatchdayEditDialog matchdayEditDialog;
    private final Stage stage;
    private boolean okWasClicked = false;

    private MatchdayEditDialogController() {
        throw new AssertionError("default constructor accidentally invoked from with class MatchdayEditDialogController");
    }

    public MatchdayEditDialogController(final Window owner, final LocalDate date) {
        this.matchdayEditDialog = new MatchdayEditDialog(date);
        matchdayEditDialog.getBtnOk().setOnAction(e -> {
            okWasClicked = true;
            closeMatchdayEditDialog();
        });
        matchdayEditDialog.getBtnCancel().setOnAction(e -> closeMatchdayEditDialog());

        this.stage = new Stage();
        stage.setTitle("Spieltag bearbeiten");
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

    public boolean okWasClicked() {
        return this.okWasClicked;
    }
}
