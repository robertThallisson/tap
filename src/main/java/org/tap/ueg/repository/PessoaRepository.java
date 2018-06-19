package org.tap.ueg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tap.ueg.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, String>{

}
