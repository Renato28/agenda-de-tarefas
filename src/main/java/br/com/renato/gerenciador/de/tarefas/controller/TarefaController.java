package br.com.renato.gerenciador.de.tarefas.controller;

import br.com.renato.gerenciador.de.tarefas.dto.TarefaDTO;
import br.com.renato.gerenciador.de.tarefas.service.TarefaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @Transactional
    @PostMapping
    public ResponseEntity<TarefaDTO> cadastrarTarefa(@RequestBody @Valid TarefaDTO dto, UriComponentsBuilder builder) {

        TarefaDTO tarefaDTO = tarefaService.cadastrarTarefa(dto);
        URI endereco = builder.path("tarefas/{id}").buildAndExpand(tarefaDTO.getId()).toUri();
        return ResponseEntity.created(endereco).body(tarefaDTO);
    }

    @GetMapping
    public Page<TarefaDTO> listarTarefas(@PageableDefault(size = 3) Pageable paginacao) {
          return tarefaService.listarTarefas(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> detalhar(@PathVariable @NotNull Integer id) {
        TarefaDTO dto = tarefaService.obterPorId(id);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTO> atualizar(@PathVariable @NotNull Integer id, @RequestBody @Valid TarefaDTO dto) {
        TarefaDTO atualizada = tarefaService.atualizarTarefa(id, dto);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TarefaDTO> remover(@PathVariable @NotNull Integer id) {
        tarefaService.excluirTarefa(id);
        return ResponseEntity.noContent().build();
    }

}