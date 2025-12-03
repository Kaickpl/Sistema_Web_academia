package br.com.upe.academia.AcademiaWeb.Controllers;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.MedidasCorporaisRegistroDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.MedidasCorporaisResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.MedidasCorporais;
import br.com.upe.academia.AcademiaWeb.Services.MedidasCorporaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/medidas")
public class MedidasCorporaisController {
    @Autowired
    private MedidasCorporaisService medidasCorporaisService;

    @GetMapping("/historico/{alunoId}")
    public List<MedidasCorporaisResponseDTO> ListarMedidasCorporais(@PathVariable UUID alunoId){
        return medidasCorporaisService.mostrarHistoricoMedidasCorporais(alunoId);
    }

    @GetMapping("/{alunoId}")
    public MedidasCorporaisResponseDTO mostrarMedidasAtuais(@PathVariable UUID alunoId){
        return medidasCorporaisService.mostrarMedidasAtuais(alunoId);
    }

    @PostMapping
    public ResponseEntity<MedidasCorporaisRegistroDTO> registrarMedidasCorporais(@RequestBody MedidasCorporaisRegistroDTO medidasCorporaisDTOs){
        MedidasCorporais novasMedidas = medidasCorporaisService.registrarMedidas(medidasCorporaisDTOs);
        MedidasCorporaisRegistroDTO dto = new MedidasCorporaisRegistroDTO(novasMedidas);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
