package org.tap.ueg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tap.ueg.models.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, String>{

}
