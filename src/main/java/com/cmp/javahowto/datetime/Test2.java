package com.cmp.javahowto.datetime;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

@Slf4j
public class Test2 {

    public static void main(String[] args) {
        Instant instant = Instant.now();
        ZonedDateTime zonedDt = ZonedDateTime.now();
        OffsetDateTime offsetDt = OffsetDateTime.now();
        LocalDateTime localDt = LocalDateTime.now();

        log.info("instant {}", instant);
        log.info("zonedDt {}", zonedDt);
        log.info("offsetDt {}", offsetDt);
        log.info("localDt {}", localDt);
    }

}
