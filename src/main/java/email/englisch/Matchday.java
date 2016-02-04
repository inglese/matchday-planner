package email.englisch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Manages days where matches can take place
 */
public class Matchday implements Comparable<Matchday>, Serializable {

    private static final Logger LOGGER = LogManager.getLogger(Matchday.class);

    private final LocalDate date;
    private final List<Match> matches = new ArrayList<>();

    private Matchday() {
        throw new AssertionError("default constructor accidentally invoked from within class Matchday");
    }

    private Matchday(LocalDate date) {
        this.date = date;
        LOGGER.debug("Created {}", this.toString());
    }

    /**
     * Generates a Matchday at the given date
     *
     * @param  date when the matchday takes place
     * @return  the generated Matchday
     */
    public static Matchday on(LocalDate date) {
        Objects.requireNonNull(date, "date must not be null");
        return new Matchday(LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth()));
    }

    /**
     * Inserts the specified match into the matchday.
     * <p>
     * The match is automatically inserted at the correct position regarding its ordering,
     * so an index does where to insert it does not have to be provided.
     *
     * @param match  the match to be inserted, not null
     */
    public void insert(Match match) {
        Objects.requireNonNull("match must not be null");
        matches.add(match);
    }

    /**
     * Returns a sequential stream of matches that take place on this matchday.
     *
     * @return a sequential stream over the matchdays on this matchday
     */
    public Stream<Match> matchStream() {
        return matches.stream();
    }

    /**
     * Outputs this matchday as a {@code String}, such as {@code Matchday('2015-12-24')}.
     *
     * @return  a string representation of this matchday, not null
     */
    @Override
    public String toString() {
        return "Matchday('" + date.toString() + "')";
    }

    /**
     * Checks if this matchday is equal to another matchday.
     * <p>
     * Compares this {@code Matchday} with another ensuring these are the same.
     *
     * @param other  the object to check, null returns false
     * @return true if this is equal to the other matchday
     */
    @Override
    final public boolean equals(final Object other) {
        if (other == null)
            return false;
        if (this == other)
            return true;
        if (!(other instanceof Matchday))
            return false;

        final Matchday matchday = (Matchday)other;
        return this.date.equals(matchday.date);
    }

    /**
     * A hash code for this matchday.
     *
     * @return a suitable hash code
     */
    @Override
    final public int hashCode() {
        return Objects.hash(this.date);
    }

    /**
     * Compares this matchday to another matchday.
     * <p>
     * The comparison is based on the date, from earliest to latest.
     * It is "consistent with equals", as defined by {@link Comparable}.
     *
     * @param otherMatchday  the other matchday to compare to, not null
     * @return the comparator value, negative if less, positive if greater, zero if equal
     */
    @Override
    public int compareTo(final Matchday otherMatchday) {
        Objects.requireNonNull(otherMatchday, "otherMatchday must not be null");

        return date.compareTo(otherMatchday.date);
    }

    /**
     * Gets the date when the matchday takes place
     *
     * @return the date of the matchday, not null
     */
    public LocalDate getDate() {
        return LocalDate.from(this.date);
    }

    // writeReplace method for the serialization proxy pattern
    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    // readObject method for the serialization proxy pattern
    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }

    // Serialization proxy for class Matchday
    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = 1L;

        private final LocalDate date;

        SerializationProxy(Matchday matchday) {
            this.date = matchday.date;
        }

        private Object readResolve() {
            return Matchday.on(this.date);
        }
    }
}
