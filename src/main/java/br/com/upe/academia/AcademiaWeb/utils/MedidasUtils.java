package br.com.upe.academia.AcademiaWeb.utils;

import br.com.upe.academia.AcademiaWeb.Entities.MedidasCorporais;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MedidasUtils {
    public static Double getValorPorNome(MedidasCorporais medidasCorporais, String tipoMedida) {
        if (medidasCorporais == null || tipoMedida == null){
            return null;
        }
        switch (tipoMedida.toLowerCase()) {
            case "peso": return medidasCorporais.getPeso();
            case "braco": return medidasCorporais.getBraco();
            case "abdomen": return medidasCorporais.getAbdomen();
            case "peito": return medidasCorporais.getPeito();
            case "quadril": return medidasCorporais.getQuadril();
            case "cintura": return medidasCorporais.getCintura();
            case "coxa": return medidasCorporais.getCoxa();
            case "ombro": return medidasCorporais.getOmbro();
            case "massamagra": return medidasCorporais.getMassaMagra();
            case "gordura": return medidasCorporais.getGordura();
            case "percentualagua": return medidasCorporais.getPercentualAgua();
            case "altura": return medidasCorporais.getAltura();
            default: return null;
        }
    }
}
