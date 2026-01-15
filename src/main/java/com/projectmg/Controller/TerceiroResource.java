package com.projectmg.Controller;

import com.projectmg.Domain.Dto.Despacho.DespachoCorteDTO;
import com.projectmg.Domain.Dto.Despacho.DespachoCorteRetornoDTO;
import com.projectmg.Domain.Dto.Despacho.ResolverDivergenciaDTO;
import com.projectmg.Domain.Dto.TerceiroDTO;
import com.projectmg.Domain.Entity.Despacho.DespachoCorte;
import com.projectmg.Repository.Despacho.DespachoCorteRepository;
import com.projectmg.Service.Despacho.DespachoCorteService;
import com.projectmg.Service.TerceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mg/terceiro")
public class TerceiroResource {

    @Autowired
    private TerceiroService terceiroService;
    @Autowired
    private DespachoCorteService despachoCorteService;
    @Autowired
    private DespachoCorteRepository despachoCorteRepository;

    // =====================
    // TERCEIROS
    // =====================
    @GetMapping({"/", ""})
    public ResponseEntity<List<TerceiroDTO>> getAllTerceiros() {
        return ResponseEntity.ok(terceiroService.buscarTerceiros());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<TerceiroDTO> buscarTerceiroPorId(@PathVariable Long id) {
        return ResponseEntity.ok(terceiroService.buscarTerceiroPorId(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<TerceiroDTO> cadastrarTerceiro(@RequestBody TerceiroDTO terceiroDTO) {
        return ResponseEntity.ok(terceiroService.cadastrarTerceiro(terceiroDTO));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarTerceiro(@PathVariable Long id) {
        terceiroService.deletarTerceiro(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<TerceiroDTO> atualizarTerceiro(
            @PathVariable Long id,
            @RequestBody TerceiroDTO terceiroDTO
    ) {
        return ResponseEntity.ok(terceiroService.atualizarTerceiro(terceiroDTO));
    }

    // =====================
    // DESPACHO DE CORTE
    // =====================

    @GetMapping("/despachos/corte")
    public ResponseEntity<List<DespachoCorte>> listarTodos() {
        return ResponseEntity.ok(despachoCorteService.listarTodos());
    }

    @GetMapping("/despachos/corte/filtro")
    public List<DespachoCorte> filtrar(
            @RequestParam(required = false) Long terceiroId,
            @RequestParam(required = false) Boolean pendente,
            @RequestParam(required = false) Boolean divergencia
    ) {
        return despachoCorteService.filtrar(terceiroId, pendente, divergencia);
    }

    @PostMapping("/despachos/corte/criar")
    public ResponseEntity<DespachoCorte> criarDespachoCorte(
            @RequestBody DespachoCorteDTO dto
    ) {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            throw new RuntimeException("Usuário não autenticado");
        }

        String usuario = auth.getName();

        return ResponseEntity.ok(
                despachoCorteService.criarDespacho(dto, usuario)
        );
    }

    @PostMapping("/despachos/corte/{id}/retorno")
    public ResponseEntity<?> registrarRetorno(
            @PathVariable Long id,
            @RequestBody DespachoCorteRetornoDTO dto,
            @AuthenticationPrincipal UserDetails user
    ) {
        return ResponseEntity.ok(
                despachoCorteService.registrarRetorno(id, dto, user.getUsername())
        );
    }

    @GetMapping("/despachos/corte/pendentes-retorno")
    public List<DespachoCorte> listarPendentes() {
        return despachoCorteRepository.findByRetornoIsNull();
    }

    @GetMapping("/despachos/corte/com-divergencia")
    public List<DespachoCorte> listarComDivergencia() {
        return despachoCorteRepository
                .findByPossuiDivergenciaTrueAndDivergenciaResolvidaFalse();
    }

    @PostMapping("/despachos/corte/{id}/resolver-divergencia")
    public ResponseEntity<?> resolverDivergencia(
            @PathVariable Long id,
            @RequestBody ResolverDivergenciaDTO dto,
            @AuthenticationPrincipal UserDetails user
    ) {
        despachoCorteService.resolverDivergencia(id, user.getUsername());
        return ResponseEntity.ok().build();
    }
}
