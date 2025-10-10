package br.com.upe.academia.AcademiaWeb.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.time.Duration;

public class DurationSerializer extends StdSerializer<Duration> {

    public DurationSerializer() {
        this(null);
    }

    public DurationSerializer(Class<Duration> t) {
        super(t);
    }

    @Override
    public void serialize(Duration duration, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (duration == null) {
            jsonGenerator.writeNull();
            return;
        }
        // Converte Duration para String formatada (ex: 00:01:45)
        long seconds = duration.getSeconds();
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long remainingSeconds = seconds % 60;

        String formattedDuration = String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
        jsonGenerator.writeString(formattedDuration);
    }
}