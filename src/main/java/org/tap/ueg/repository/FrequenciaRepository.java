package org.tap.ueg.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tap.ueg.models.Aluno;
import org.tap.ueg.models.Frequencia;

public interface FrequenciaRepository  extends JpaRepository<Frequencia, String> {
	Iterable<Frequencia> findByRegistro(Date data);
	Iterable<Frequencia> findByAlunoAndRegistro(Aluno aluno,Date date);
	
}
