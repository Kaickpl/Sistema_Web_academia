package br.com.upe.academia.AcademiaWeb;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Converte o objeto java.time.Duration para o formato String 'HH:MM:SS'
 * e vice-versa, para persistência no banco de dados.
 */
@Converter(autoApply = true) // autoApply = true tenta aplicar a todos os campos Duration
public class DurationCustomConverter implements AttributeConverter<Duration, String> {

    // Regex para capturar e formatar HH:MM:SS
    private static final Pattern TIME_PATTERN = Pattern.compile("(\\d{2}):(\\d{2}):(\\d{2})");

    @Override
    public String convertToDatabaseColumn(Duration duration) {
        if (duration == null) {
            return null;
        }
        // Converte a Duration (ex: 150 segundos) para String formatada (ex: 00:02:30)
        long seconds = duration.getSeconds();
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long remainingSeconds = seconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }

    @Override
    public Duration convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        // Converte String formatada (ex: 00:03:00) de volta para Duration (Java)
        Matcher matcher = TIME_PATTERN.matcher(dbData);
        if (matcher.matches()) {
            long hours = Long.parseLong(matcher.group(1));
            long minutes = Long.parseLong(matcher.group(2));
            long seconds = Long.parseLong(matcher.group(3));

            return Duration.ofSeconds(hours * 3600 + minutes * 60 + seconds);
        }
        // Lança exceção se o formato não for reconhecido (útil para debug de SQL)
        throw new IllegalArgumentException("Formato de duração inválido no banco de dados: " + dbData);
    }
}