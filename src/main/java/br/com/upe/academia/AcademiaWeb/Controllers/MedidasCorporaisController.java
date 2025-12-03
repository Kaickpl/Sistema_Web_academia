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

@RestController
@RequestMapping("/medidas")
@CrossOrigin(origins = "*")
public class MedidasCorporaisController {
    @Autowired
    private MedidasCorporaisService medidasCorporaisService;

    @GetMapping("/{alunoId}/historico")
    public List<MedidasCorporaisResponseDTO> ListarMedidasCorporais(@PathVariable UUID alunoId){
        return medidasCorporaisService.mostrarHistoricoMedidasCorporais(alunoId);
    }

    @GetMapping("/{alunoId}/historico/10")
    public List<MedidasCorporaisResponseDTO> Listar10MedidasCorporais(@PathVariable UUID alunoId){
        return medidasCorporaisService.mostrar10Medidas(alunoId);
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
