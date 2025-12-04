package br.com.upe.academia.AcademiaWeb.Entities.Enums;

import br.com.upe.academia.AcademiaWeb.Exceptions.ValorInvalidoException;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum MusculoTrabalhado {
    PEITORAL,
    COSTAS,
    QUADRICEPS,
    POSTERIROR_DE_COXA,
    GLUTEOS,
    OMBRO,
    BICEPS,
    TRICEPS,
    PANTURRILHA,
    CORE,
    CARDIO;

    @JsonCreator
    public static MusculoTrabalhado fromString(String musculo) {
        if (musculo == null) {
            return null;
        }

        try {
            return MusculoTrabalhado.valueOf(musculo.toUpperCase());
        } catch (IllegalArgumentException e) {
            String valoresValidos = Arrays.toString(MusculoTrabalhado.values());

            throw new ValorInvalidoException( String.format("Valor de músculo trabalhado '%s' inválido! Aqui estão as opções possíveis: %s", musculo, valoresValidos));
        }

    }
}


