package email.englisch.builders;

import email.englisch.Match;

import java.time.LocalTime;

public class MatchBuilder {
    private final LocalTime time;

    public static MatchBuilder aMatchAt(LocalTime time) {
        return new MatchBuilder(time);
    }

    private MatchBuilder(LocalTime time) {
        this.time = time;
    }

    public Match build() {
        return Match.at(time);
    }
}
