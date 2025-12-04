package br.com.upe.academia.AcademiaWeb.utils;

import br.com.upe.academia.AcademiaWeb.Exceptions.ValorInvalidoException;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DurationManager {

    private static final Pattern TIME_PATTERN = Pattern.compile("(\\d{2}):(\\d{2}):(\\d{2})");

    public static Duration parseDuration(String durationString) {
        if (durationString == null || durationString.isEmpty()) {
            return Duration.ZERO;
        }

        Matcher matcher = TIME_PATTERN.matcher(durationString);
        if (matcher.matches()) {
            long hours = Long.parseLong(matcher.group(1));
            long minutes = Long.parseLong(matcher.group(2));
            long seconds = Long.parseLong(matcher.group(3));

            return Duration.ofSeconds(hours * 3600 + minutes * 60 + seconds);
        }

        throw new ValorInvalidoException("O valor do tempo está inválido. Por favor, escreva o tempo no seguinte padrão: hh:mm:ss");
    }

    public static String formatDuration(Duration duration) {
        if (duration == null || duration.isNegative()) {
            return "00:00:00";
        }
        long seconds = duration.getSeconds();
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long remainingSeconds = seconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }
}