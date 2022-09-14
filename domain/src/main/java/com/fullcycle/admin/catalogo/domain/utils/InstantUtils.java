package com.fullcycle.admin.catalogo.domain.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public final class InstantUtils {

    private InstantUtils() {
    }

    /*
       When compared an Instant.now() with a value fetched from database, there is a difference
       Instant.now() has precision 9. Instant got from database has precision 6

       https://bugs.openjdk.org/browse/JDK-8266563
       https://igniterealtime.atlassian.net/browse/OF-1738
    */
    public static Instant now() {
        return Instant.now().truncatedTo(ChronoUnit.MICROS);
    }
}