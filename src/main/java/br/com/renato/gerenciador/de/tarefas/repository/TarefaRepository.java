package br.com.renato.gerenciador.de.tarefas.repository;

import br.com.renato.gerenciador.de.tarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    
}
