package br.com.upe.academia.AcademiaWeb.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.Duration;

@Converter
public class DurationCustomConverter implements AttributeConverter<Duration, String> {

    @Override
    public String convertToDatabaseColumn(Duration duration) {
        return DurationManager.formatDuration(duration);
    }


    @Override
    public Duration convertToEntityAttribute(String dbData) {
        return DurationManager.parseDuration(dbData);
    }
}