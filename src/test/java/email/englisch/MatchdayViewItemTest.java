package email.englisch;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

import static email.englisch.builders.MatchdayBuilder.aMatchdayOn;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.rules.ExpectedException.none;

public class MatchdayViewItemTest {

    @Rule
    public ExpectedException expectedException = none();

    @Test
    public void creation_with_null_date_should_throw_NPE() {
        expectedException.expect(NullPointerException.class);
        final MatchdayViewItem matchdayViewItem = new MatchdayViewItem(null);
    }

    @Test
    public void matchday_getter_should_return_the_matchday_this_instance_was_created_for() {
        final Matchday matchday = aMatchdayOn(LocalDate.now()).build();
        final MatchdayViewItem matchdayViewItem = new MatchdayViewItem(matchday);

        final Matchday returnedMatchday = matchdayViewItem.getMatchday();
        final Matchday expectedMatchday = aMatchdayOn(LocalDate.now()).build();

        assertThat(returnedMatchday, is(equalTo(expectedMatchday)));
    }
}
