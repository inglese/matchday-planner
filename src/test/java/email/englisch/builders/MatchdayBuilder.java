package email.englisch.builders;

import email.englisch.Matchday;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatchdayBuilder {
    private final LocalDate date;
    private final List<MatchBuilder> matchBuilders = new ArrayList<>();

    public static MatchdayBuilder aMatchdayOn(LocalDate date) {
        return new MatchdayBuilder(date);
    }

    private MatchdayBuilder(LocalDate date) {
        this.date = date;
    }

    public Matchday build() {
        return Matchday.on(date);
    }

    public MatchdayBuilder with(MatchBuilder matchBuilder) {
        matchBuilders.add(matchBuilder);
        return this;
    }
}
