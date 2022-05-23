package com.lechos22j.wisniamobile.utils;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.Date;

public class Tools {
    public static Date offsetDate(Date date, int offset) {
        return new Date(date.getTime() + offset);
    }
    public static Date offsetByMonths(Date date, int offset) {
        LocalDate localDate = date.toInstant().atOffset(ZoneOffset.UTC).toLocalDate();
        localDate = localDate.plusMonths(1);
        return Date.from(localDate.atStartOfDay().toInstant(ZoneOffset.UTC));
    }
}
