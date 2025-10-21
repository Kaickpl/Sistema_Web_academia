package br.com.upe.academia.AcademiaWeb.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DurationDeserializer extends StdDeserializer<Duration> {

    private static final Pattern TIME_PATTERN = Pattern.compile("(\\d{2}):(\\d{2}):(\\d{2})");

    public DurationDeserializer() {
        this(null);
    }

    public DurationDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Duration deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String value = jsonParser.getText();
        Matcher matcher = TIME_PATTERN.matcher(value);

        if (matcher.matches()) {
            long hours = Long.parseLong(matcher.group(1));
            long minutes = Long.parseLong(matcher.group(2));
            long seconds = Long.parseLong(matcher.group(3));

            return Duration.ofSeconds(hours * 3600 + minutes * 60 + seconds);
        }

        // Se a string não estiver no formato HH:MM:SS, tente o padrão ISO para compatibilidade
        try {
            return Duration.parse(value);
        } catch (Exception e) {
            throw new IOException("Formato de duração JSON inválido. Esperado HH:MM:SS ou ISO 8601.", e);
        }
    }
}