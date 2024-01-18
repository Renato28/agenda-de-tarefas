package br.com.renato.gerenciador.de.tarefas.dto;

import br.com.renato.gerenciador.de.tarefas.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TarefaDTO {

    private Integer id;

    private String nome;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private Status status;
}
