package org.tap.ueg.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.tap.ueg.models.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, String> {

	Iterable<Aluno> findByFrequenciaRegistro(LocalDateTime data);

	Iterable<Aluno> findByFrequenciaRegistroBetween(LocalDateTime start, LocalDateTime end);

	// @Query(" select from Aluno aluno left outer join Frequencia frequencia on"
	// + " aluno.id = frequencia.aluno_id where frequencia.registro between :p1 and
	// :p2") //
	@Modifying
	@Query(nativeQuery = true, value = " select * from aluno left outer join frequencia on aluno.id=frequencia.aluno_id where frequencia.registro between :p1 and :p2")
	Iterable<Aluno> findByFrequenciaQuery(@Param("p1") Timestamp data, @Param("p2") Timestamp data2);

	// Iterable<Aluno>
	// findAllByFrequenciaRegistroDateLessThanEqualAnFrequenciaRegistroDateGreaterThanEqual(OffsetDateTime
	// data,OffsetDateTime data2);

	// @Query("SELECT * FROM Aluno t where t.id = :id")
	// Iterable<Aluno> findByFrequenciaRegistro(Date data);
	Aluno findByDigitaisNome(String nome);

	Aluno findByMatricula(String matricula);
	// @Query("SELECT t.title FROM Todo t where t.id = :id")
	// Aluno findByDigitaisId(String nome);
}
