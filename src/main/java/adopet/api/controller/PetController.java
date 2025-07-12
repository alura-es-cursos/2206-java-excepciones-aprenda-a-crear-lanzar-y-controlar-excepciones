package adopet.api.controller;

import adopet.api.dto.RegistroPetDTO;
import adopet.api.dto.PetDTO;
import adopet.api.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("pets")
public class PetController {

    @Autowired
    private PetService service;

    @GetMapping
    public ResponseEntity<List<PetDTO>> buscarTodos(){
        List<PetDTO> pets = service.listarTodos();
        return ResponseEntity.ok(pets);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> registrar(@RequestPart @Valid RegistroPetDTO datos,
                                            @RequestParam MultipartFile imagen){
        try{
            service.registrar(datos, imagen);
        }catch (IOException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
