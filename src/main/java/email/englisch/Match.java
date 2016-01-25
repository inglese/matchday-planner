package email.englisch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalTime;
import java.util.Objects;

/**
 * A Match
 */
public class Match implements Comparable<Match> {
    private static final Logger LOGGER = LogManager.getLogger(Match.class);

    private final LocalTime time;

    private Match() {
        throw new AssertionError("default constructor accidentally invoked from within class Match");
    }

    private Match(LocalTime time) {
        this.time = time;
        LOGGER.debug("Created {}", this.toString());
    }

    /**
     * Generates a Match at the given time.
     *
     * @param  time when the match takes place
     * @return the generated match
     */
    public static Match at(LocalTime time) {
        Objects.requireNonNull(time, "time must not be null");
        return new Match(LocalTime.of(time.getHour(), time.getMinute()));
    }

    /**
     * Outputs this match as a {@code String}, such as {@code Match('10:30')}.
     *
     * @return  a string representation of this match, not null
     */
    @Override
    public String toString() {
        return "Match('" + time.toString() + "')";
    }

    /**
     * Checks if this match is equal to another match.
     * <p>
     * Compares this {@code Match} with another ensuring these are the same.
     *
     * @param other  the object to check, null returns false
     * @return true if this is equal to the other match
     */
    @Override
    final public boolean equals(final Object other) {
        if (other == null)
            return false;
        if (this == other)
            return true;
        if (!(other instanceof Match))
            return false;

        final Match match = (Match)other;
        return this.time.equals(match.time);
    }

    /**
     * A hash code for this match.
     *
     * @return a suitable hash code
     */
    @Override
    final public int hashCode() {
        return Objects.hash(this.time);
    }

    /**
     * Compares this match to another match.
     * <p>
     * The comparison is based on the time, from earliest to latest.
     * It is "consistent with equals", as defined by {@link Comparable}.
     *
     * @param otherMatch  the other match to compare to, not null
     * @return the comparator value, negative if less, positive if greater, zero if equal
     */
    @Override
    public int compareTo(final Match otherMatch) {
        Objects.requireNonNull(otherMatch, "otherMatch must not be null");
        return time.compareTo(otherMatch.time);
    }
}
