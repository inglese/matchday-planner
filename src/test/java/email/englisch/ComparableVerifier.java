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
        checkContrariness();
        checkTransitivity();
        checkConsistency();
        checkConsistencyToEquals();

        assertThat(base, is(greaterThan(lesser)));
        assertThat(base, is(lessThan(greater)));
        assertThat(greater, is(greaterThan(lesser)));
    }

    @SuppressWarnings("unchecked")
    private void checkContrariness() {
        assertThat("sgn(base.compareTo(similar)) should be same as -sgn(similar.compareTo(base))",
                Integer.signum(base.compareTo(similar)),
                is(equalTo(-Integer.signum(similar.compareTo(base)))));
        assertThat("sgn(base.compareTo(lesser)) should be same as -sgn(lesser.compareTo(base))",
                Integer.signum(base.compareTo(lesser)),
                is(equalTo(-Integer.signum(lesser.compareTo(base)))));
        assertThat("sgn(base.compareTo(greater)) should be same as -sgn(greater.compareTo(base))",
                Integer.signum(base.compareTo(greater)),
                is(equalTo(-Integer.signum(greater.compareTo(base)))));
    }

    @SuppressWarnings("unchecked")
    private void checkTransitivity() {
        assertThat("base should be less than greater",
                Integer.signum(base.compareTo(greater)),
                is(lessThan(0)));
        assertThat("base should be greater than lesser",
                Integer.signum(base.compareTo(lesser)),
                is(greaterThan(0)));
        assertThat("lesser should be less than greater",
                Integer.signum(lesser.compareTo(greater)),
                is(lessThan(0)));
    }

    @SuppressWarnings("unchecked")
    private void checkConsistency() {
        assertThat("base.compareTo(similar) should return 0",
                base.compareTo(similar),
                is(0));
        assertThat("sgn(base.compareTo(lesser) should be sames as sgn(similar.compareTo(lesser))",
                Integer.signum(base.compareTo(lesser)),
                is(equalTo(Integer.signum(similar.compareTo(lesser)))));
        assertThat("sgn(base.compareTo(greater) should be same as sgn(similar.compareTo(greater))",
                Integer.signum(base.compareTo(greater)),
                is(equalTo(Integer.signum(similar.compareTo(greater)))));
    }

    @SuppressWarnings("unchecked")
    private void checkConsistencyToEquals() {
        assertThat("compareTo should be consistent to equals",
                (base.compareTo(similar) == 0),
                is(equalTo(base.equals(similar))));
    }
}
