package com.projectmg.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mg/ordem-corte")
public class OrdemCorteResource {


    @GetMapping({"","/"})
    public ResponseEntity<List<OrdemCorteResource>> getOrdemCorte(){
        return
    }
    @PostMapping("/criar")
    public ResponseEntity<OrdemCorteResource> criar(@RequestBody OrdemCorteResource ordemCorteResource) {
        return
    }

    @PostMapping("/{id}/converter-despacho")
    public ResponseEntity<OrdemCorteResource> converter(@PathVariable String id, @RequestBody OrdemCorteResource ordemCorteResource) {
        return
    }

}
