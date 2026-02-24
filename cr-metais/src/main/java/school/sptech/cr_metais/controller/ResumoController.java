package school.sptech.cr_metais.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.cr_metais.dto.Resumo.ResumoDto;
import school.sptech.cr_metais.service.ResumoService;

@RestController
@RequestMapping("/resumos")
public class ResumoController {

    @Autowired
    private ResumoService service;

    @GetMapping
    public ResponseEntity<ResumoDto> buscarResumo(){

        ResumoDto resumoDto = service.buscarResumo();

        return ResponseEntity.status(200).body(resumoDto);
    }
}
