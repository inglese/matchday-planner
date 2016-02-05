package email.englisch;

import email.englisch.builders.MatchBuilder;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static email.englisch.builders.MatchBuilder.aMatchAt;
import static email.englisch.builders.MatchdayBuilder.aMatchdayOn;
import static org.junit.rules.ExpectedException.none;

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

    @Test
    public void test_creation_of_matchday_with_match() {
        final Matchday matchday = aMatchdayOn(LocalDate.now())
                .with(aMatchAt(LocalTime.of(10, 0)))
                .build();
        final List<Match> matches = matchday.matchStream().collect(Collectors.toList());
        final Match expectedMatch = Match.at(LocalTime.of(10, 0));
        assertThat(matches, contains(expectedMatch));
    }

    @Test
    public void inserted_matches_should_be_ordered_correctly() {
        final MatchBuilder earlierMatchBuilder = aMatchAt(LocalTime.of(10, 0));
        final MatchBuilder laterMatchBuilder = aMatchAt(LocalTime.of(12, 0));
        final Matchday matchday = aMatchdayOn(LocalDate.now())
                .with(laterMatchBuilder)
                .with(earlierMatchBuilder)
                .build();
        final List<Match> insertedMatches = matchday.matchStream().collect(Collectors.toList());

        assertThat(insertedMatches, contains(earlierMatchBuilder.build(), laterMatchBuilder.build()));
    }
}
