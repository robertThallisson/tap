package org.tap.ueg.repository;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tap.ueg.models.Aluno;
import org.tap.ueg.models.Frequencia;

public interface FrequenciaRepository  extends JpaRepository<Frequencia, String> {
	Iterable<Frequencia> findByRegistro(LocalDateTime data);
	Iterable<Frequencia> findByAlunoAndRegistroBetween(Aluno aluno, LocalDateTime start, LocalDateTime end);
	Iterable<Frequencia> findByAlunoAndRegistro(Aluno aluno,Date date);
	
}
