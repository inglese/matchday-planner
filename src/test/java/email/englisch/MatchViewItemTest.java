package email.englisch;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalTime;

import static email.englisch.builders.MatchBuilder.aMatchAt;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.rules.ExpectedException.none;

public class MatchViewItemTest {

    @Rule
    public ExpectedException expectedException = none();

    @Test
    public void creation_with_null_date_should_throw_NPE() {
        expectedException.expect(NullPointerException.class);
        @SuppressWarnings("unused") final MatchViewItem matchViewItem = new MatchViewItem(null);
    }

    @Test
    public void match_getter_should_return_the_match_this_instance_was_created_for() {
        final Match match = aMatchAt(LocalTime.of(10, 0)).build();
        final MatchViewItem matchViewItem = new MatchViewItem(match);

        final Match returnedMatch = matchViewItem.getMatch();
        final Match expectedMatch = aMatchAt(LocalTime.of(10, 0)).build();

        assertThat(returnedMatch, is(equalTo(expectedMatch)));
    }
}