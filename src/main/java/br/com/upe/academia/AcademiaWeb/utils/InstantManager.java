package br.com.upe.academia.AcademiaWeb.utils;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class InstantManager {
    private static final ZoneId BRASIL_ZONE = ZoneId.of("America/Sao_Paulo");

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


    public static String formatInstantToLocalTime(Instant instant) {
        if (instant == null) {
            return null;
        }

        ZonedDateTime zonedDateTime = instant.atZone(BRASIL_ZONE);

        return zonedDateTime.format(FORMATTER);
    }
}
