package email.englisch;

import javafx.scene.Node;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;

/**
 * Item to be displayed for a matchday in {@code MatchdaysView}.
 */
public class MatchdayViewItem implements MatchdaysViewItem {

    private Matchday matchday;

    /**
     * Generates a {@code MatchdayViewItem} for the given matchday.
     *
     * @param  matchday the matchday which this {@code MatchdayViewItem} represents, not null
     */
    public MatchdayViewItem(Matchday matchday) {
        Objects.requireNonNull(matchday, "matchday must not be null");
        this.matchday = matchday;
    }

    /**
     * Returns a graphical representation of the {@code Matchday} of this instance
     *
     * @return Node to be used as graphical representation in the {@code MatchdayView}, not null
     */
    @Override
    public Node getGraphic() {
        final LocalDate matchdayDate = matchday.getDate();
        return new Label(matchdayDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
    }

    /**
     * Returns the matchday that this class is used to show in {@code MatchdaysView}.
     *
     * @return the matchday that this matchday view item represents, not null
     */
    public Matchday getMatchday() {
        return matchday;
    }
}
