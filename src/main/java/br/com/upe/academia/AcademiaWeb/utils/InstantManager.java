package br.com.upe.academia.AcademiaWeb.utils;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class InstantManager {
    // Fuso horário padrão do Brasil (São Paulo)
    private static final ZoneId BRASIL_ZONE = ZoneId.of("America/Sao_Paulo");

    // Define o formato de exibição: DD/MM/AAAA HH:MM:SS
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /**
     * Converte um Instant (UTC) para uma String formatada na hora local do Brasil.
     * @param instant O momento universal (UTC) a ser formatado.
     * @return String formatada como 'DD/MM/AAAA HH:MM:SS'.
     */
    public static String formatInstantToLocalTime(Instant instant) {
        if (instant == null) {
            return null;
        }

        // 1. Converte o Instant (UTC) para ZonedDateTime (Hora Local BR)
        ZonedDateTime zonedDateTime = instant.atZone(BRASIL_ZONE);

        // 2. Formata para String legível
        return zonedDateTime.format(FORMATTER);
    }
}
