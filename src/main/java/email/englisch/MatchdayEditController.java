package email.englisch;

import javafx.scene.Scene;

import java.time.LocalDate;

public class MatchdayEditController {

    private final MatchdayEditView matchdayEditView;

    private MatchdayEditController() {
        throw new AssertionError("default constructor accidentally invoked from with class MatchdayEditController");
    }

    public MatchdayEditController(final LocalDate date) {
        this.matchdayEditView = new MatchdayEditView(date);
    }

    public Scene getScene() {
        return this.matchdayEditView.getScene();
    }
}
