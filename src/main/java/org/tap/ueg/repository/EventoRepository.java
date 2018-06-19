package org.tap.ueg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tap.ueg.models.Evento;

public interface EventoRepository extends JpaRepository<Evento, String>{

	Evento findByCodigo(long codigo);
}
