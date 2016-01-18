package email.englisch;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

/**
 * Helps to create a new matchday by controlling a matchday edit dialog.
 */
public class MatchdayEditController {

    private final MatchdayEditDialog matchdayEditDialog;
    private final Stage stage;
    private Optional<Matchday> createdMatchday;

    private MatchdayEditController() {
        throw new AssertionError("default constructor accidentally invoked from within class MatchdayEditController");
    }

    /**
     * Creates a matchday edit controller with the specified owner window for the matchday edit dialog
     * and the specified date as default date
     *
     * @param owner  owner window of the matchday edit dialog, not null
     * @param date  default date for the date when the matchday takes place, not null
     */
    public MatchdayEditController(final Window owner, final LocalDate date) {
        Objects.requireNonNull(owner, "owner must not be null");
        Objects.requireNonNull(date, "date must not be null");
        this.matchdayEditDialog = new MatchdayEditDialog(date);
        matchdayEditDialog.onOkClicked((e -> {
            createdMatchday = Optional.of(Matchday.on(matchdayEditDialog.getDate()));
            closeMatchdayEditDialog();
        }));
        matchdayEditDialog.onCancelClicked((e -> {
            createdMatchday = Optional.empty();
            closeMatchdayEditDialog();
        }));

        this.stage = new Stage();
        stage.setTitle("Neuer Spieltag");
        stage.setScene(matchdayEditDialog.getScene());
        stage.initOwner(owner);
        stage.initModality(Modality.WINDOW_MODAL);
    }

    /**
     * Shows the matchday edit dialog and waits until it's closed
     */
    public void showMatchdayEditDialogAndWait() {
        this.stage.showAndWait();
    }

    /**
     * Returns an {@code Optional}, that contains a matchday if one was created, null otherwise
     *
     * @return the optional containing the matchday, if it was created, null otherwise
     */
    public Optional<Matchday> getCreatedMatchday() {
        return this.createdMatchday;
    }

    private void closeMatchdayEditDialog() {
        this.stage.hide();
    }
}
