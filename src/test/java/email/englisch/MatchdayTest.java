package email.englisch;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.rules.ExpectedException.none;

import java.time.LocalDate;

public class MatchdayTest {

    @Rule
    public ExpectedException expectedException = none();

    @Test
    public void creation_with_null_date_should_throw_NPE() {
        expectedException.expect(NullPointerException.class);
        final Matchday matchday = Matchday.on(null);
    }

    @Test
    public void test_equals_contract() {
        EqualsVerifier.forClass(Matchday.class)
                .suppress(Warning.NULL_FIELDS)
                .verify();
    }

    @Test
    public void test_compareTo_contract() {
        final LocalDate baseDate = LocalDate.now();
        final Matchday baseMatchday = Matchday.on(baseDate);
        final Matchday equalMatchday = Matchday.on(baseDate);
        final Matchday earlierMatchday = Matchday.on(baseDate.minusDays(1));
        final Matchday laterMatchday = Matchday.on(baseDate.plusDays(1));

        ComparableVerifier.forExamples(baseMatchday, equalMatchday, earlierMatchday, laterMatchday).verify();
    }
}
