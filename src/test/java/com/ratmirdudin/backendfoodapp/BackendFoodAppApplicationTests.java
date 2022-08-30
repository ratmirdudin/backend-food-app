package com.ratmirdudin.backendfoodapp;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;


class BackendFoodAppApplicationTests {

    @Test
    void test() {
        Instant instant = Instant.parse("2022-08-28T00:00:00.00Z");
        System.out.println(instant);
        Instant instantPlusTwoDays = instant.plus(2, ChronoUnit.DAYS);
        System.out.println(instantPlusTwoDays);
        int numOfDayOfWeek = instant
                .atZone(ZoneId.systemDefault())
                .getDayOfWeek()
                .getValue();
        System.out.println(numOfDayOfWeek);
    }

}
