package com.example.hellospring.learningTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class ClockTest {
    // Clock을 이용해서 LocalDateTime.now()를 반환
    @Test
    void clock() {
        Clock clock = Clock.systemDefaultZone();
        LocalDateTime dt1 = LocalDateTime.now(clock);
        LocalDateTime dt2 = LocalDateTime.now(clock);

        assertThat(dt2).isAfter(dt1);
    }

    // Clock을 Test에서 사용할 때 내가 원하는 시간을 반환하도록 설정
    @Test
    void fixedClock() {
        Clock fixedClock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        LocalDateTime dt1 = LocalDateTime.now(fixedClock);
        LocalDateTime dt2 = LocalDateTime.now(fixedClock);
        LocalDateTime dt3 = LocalDateTime.now(fixedClock).plusMinutes(30);

        assertThat(dt1).isEqualTo(dt2);
        assertThat(dt3).isEqualTo(dt1.plusMinutes(30));
        assertThat(dt3).isEqualTo(dt2.plusMinutes(30));
    }

}
