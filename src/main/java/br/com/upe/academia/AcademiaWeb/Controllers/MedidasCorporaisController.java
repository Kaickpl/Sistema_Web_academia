package br.com.upe.academia.AcademiaWeb.Controllers;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.MedidasCorporaisDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.MedidasCorporais;
import br.com.upe.academia.AcademiaWeb.Services.MedidasCorporaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/medidas")
public class MedidasCorporaisController {
    @Autowired
    private MedidasCorporaisService medidasCorporaisService;

    @GetMapping("/{alunoId}")
    public List<MedidasCorporais> ListarMedidasCorporais(@PathVariable UUID alunoId){
        return medidasCorporaisService.mostrarMedidasCorporais(alunoId);
    }

    @PostMapping
    public ResponseEntity<MedidasCorporais> registrarMedidasCorporais(@RequestBody MedidasCorporaisDTOs medidasCorporaisDTOs){
        MedidasCorporais novasMedidas = medidasCorporaisService.registrarMedidas(medidasCorporaisDTOs);
        return ResponseEntity.ok().body(novasMedidas);
    }
}
