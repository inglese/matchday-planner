package email.englisch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Manages days where matches can take place
 */
public class Matchday implements Comparable<Matchday> {

    private static final Logger LOGGER = LogManager.getLogger(Matchday.class);

    private final LocalDate date;

    private Matchday() {
        throw new AssertionError("default constructor accidentally invoked from with class Matchday");
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
}
