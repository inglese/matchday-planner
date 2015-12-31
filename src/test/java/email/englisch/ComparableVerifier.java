package email.englisch;

import java.util.Objects;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public final class ComparableVerifier<T extends Comparable> {

    private final T base;
    private final T similar;
    private final T lesser;
    private final T greater;

    private ComparableVerifier() {
        throw new AssertionError("default constructor accidentally invoked from with class Comparable Verifier");
    }

    private ComparableVerifier(final T base, final T similar, final T lesser, final T greater) {
        this.base = base;
        this.similar = similar;
        this.lesser = lesser;
        this.greater = greater;
    }

    public static <T extends Comparable> ComparableVerifier<T> forExamples(T base, T similar, T lesser, T greater) {
        Objects.requireNonNull(base);
        Objects.requireNonNull(similar);
        Objects.requireNonNull(lesser);
        Objects.requireNonNull(greater);
        return new ComparableVerifier<>(base, similar, lesser, greater);
    }

    @SuppressWarnings("unchecked")
    public void verify() {
        checkTransitivity();
        assertThat(base, is(greaterThan(lesser)));
        assertThat(base, is(lessThan(lesser)));
        assertThat(greater, is(greaterThan(lesser)));
    }

    @SuppressWarnings("unchecked")
    private void checkTransitivity() {
        final double sgn_greater_compareTo_base = Math.signum(greater.compareTo(base));
        final double sgn_base_compareTo_lesser = Math.signum(base.compareTo(lesser));
        final double sgn_greater_compareTo_lesser = Math.signum(greater.compareTo(lesser));

        assertThat(sgn_greater_compareTo_base, is(greaterThan(0.0)));
        assertThat(sgn_base_compareTo_lesser, is(greaterThan(0.0)));
        assertThat(sgn_greater_compareTo_lesser, is(greaterThan(0.0)));
    }
}
