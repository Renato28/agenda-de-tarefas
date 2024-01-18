package br.com.renato.gerenciador.de.tarefas.service;

import br.com.renato.gerenciador.de.tarefas.controller.TarefaController;
import br.com.renato.gerenciador.de.tarefas.dto.TarefaDTO;
import br.com.renato.gerenciador.de.tarefas.model.Status;
import br.com.renato.gerenciador.de.tarefas.model.Tarefa;
import br.com.renato.gerenciador.de.tarefas.repository.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TarefaDTO cadastrarTarefa(TarefaDTO dto) {

        Tarefa tarefa = modelMapper.map(dto, Tarefa.class);
        tarefa.setStatus(Status.CRIADA);
        tarefaRepository.save(tarefa);
        return modelMapper.map(tarefa, TarefaDTO.class);
    }

    public Page<TarefaDTO> listarTarefas(Pageable paginacao) {

        return tarefaRepository.findAll(paginacao)
                .map(t -> modelMapper.map(t, TarefaDTO.class));
    }

    public TarefaDTO obterPorId(Integer id) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(tarefa, TarefaDTO.class);
    }

    public TarefaDTO atualizarTarefa(Integer id, TarefaDTO dto) {
        Tarefa tarefa = modelMapper.map(dto, Tarefa.class);
        tarefa.setId(id);
        tarefa = tarefaRepository.save(tarefa);
        return modelMapper.map(tarefa, TarefaDTO.class);
    }

    public void excluirTarefa(Integer id) {
        tarefaRepository.deleteById(id);
    }

}
