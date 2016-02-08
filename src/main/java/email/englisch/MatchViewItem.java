package email.englisch;

import javafx.scene.Node;
import javafx.scene.control.Label;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;

/**
 * Item to be displayed for a match in {@code MatchdaysView}.
 */
public class MatchViewItem implements MatchdaysViewItem {

    private Match match;

    /**
     * Generates a {@code MatchViewItem} for the given match.
     *
     * @param match the match which this {@code MatchViewItem} represents, not null
     */
    public MatchViewItem(Match match) {
        Objects.requireNonNull(match, "match must not be null");
        this.match = match;
    }

    /**
     * Returns a graphical representation of the {@code Matchday} of this instance
     *
     * @return Node to be used as graphical representation in the {@code MatchdayView}, not null
     */
    @Override
    public Node getGraphic() {
        final LocalTime matchTime = match.getTime();
        return new Label(matchTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)));
    }

    /**
     * Returns the matchday that this class is used to show in {@code MatchdaysView}.
     *
     * @return the matchday that this matchday view item represents, not null
     */
    public Match getMatch() {
        return match;
    }
}
