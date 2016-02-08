package email.englisch;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalTime;

import static org.junit.rules.ExpectedException.none;

public class MatchTest {

    @Rule
    public ExpectedException expectedException = none();

    @Test
    public void creation_with_null_time_should_throw_NPE() {
        expectedException.expect(NullPointerException.class);
        @SuppressWarnings("unused") final Match match = Match.at(null);
    }

    @Test
    public void test_compareTo_contract() {
        final LocalTime baseTime = LocalTime.now();
        final Match baseMatch = Match.at(baseTime);
        final Match equalMatch = Match.at(baseTime);
        final Match earlierMatch = Match.at(baseTime.minusHours(1));
        final Match laterMatch = Match.at(baseTime.plusHours(1));

        ComparableVerifier.forExamples(baseMatch, equalMatch, earlierMatch, laterMatch).verify();
    }
}