package adopet.api.controller;

import adopet.api.dto.AdopcionDTO;
import adopet.api.dto.AprobarAdopcionDTO;
import adopet.api.dto.RechazarAdopcionDTO;
import adopet.api.dto.SolicitudDeAdopcionDTO;
import adopet.api.service.AdopcionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("adopcion")
public class AdopcionController {

    @Autowired
    private AdopcionService service;

    @GetMapping
    public ResponseEntity<List<AdopcionDTO>> buscarTodos(){
        List<AdopcionDTO> adopciones= service.listarTodos();
        return ResponseEntity.ok(adopciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdopcionDTO> buscar(@PathVariable Long id){
        AdopcionDTO adopcion = service.listar(id);
        return ResponseEntity.ok(adopcion);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> solicitar(@RequestBody @Valid SolicitudDeAdopcionDTO datos){
        this.service.solicitar(datos);
        return ResponseEntity.ok("Adopción solicitada con éxito!");
    }

    @PutMapping("/aprobar")
    @Transactional
    public ResponseEntity<String> aprobar(@RequestBody @Valid AprobarAdopcionDTO dto){
        try{
            this.service.aprobar(dto);
        }catch (EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Adopción no encontrada -> "+ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/rechazar")
    @Transactional
    public ResponseEntity<String> rechazar(@RequestBody @Valid RechazarAdopcionDTO dto){
        this.service.rechazar(dto);
        return ResponseEntity.ok().build();
    }
}
