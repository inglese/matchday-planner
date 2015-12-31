package email.englisch;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.time.LocalDate;
import java.time.Month;

public class LocalDateTest {

    @Test
    public void test_string_representation() {
        final LocalDate myBirthday = LocalDate.of(1967, Month.SEPTEMBER, 18);

        assertThat("toString", myBirthday.toString(), is("1967-09-18"));
    }

    @Test
    public void test_plusXYZ_methods() {
        final LocalDate myBirthday = LocalDate.of(1967, Month.SEPTEMBER, 18);

        assertThat("plusDays", myBirthday.plusDays(1).toString(), is("1967-09-19"));
        assertThat("plusMonths", myBirthday.plusMonths(1).toString(), is("1967-10-18"));
        assertThat("plusYears", myBirthday.plusYears(1).toString(), is("1968-09-18"));
    }
}
