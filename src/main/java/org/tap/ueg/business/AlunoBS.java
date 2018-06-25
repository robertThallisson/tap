package org.tap.ueg.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tap.ueg.models.Digital;
import org.tap.ueg.repository.AlunoRepository;
import org.tap.ueg.repository.DigitaisRepository;
import org.tap.ueg.repository.FrequenciaRepository;

@Service
public class AlunoBS {

	@Autowired
	private FrequenciaRepository fr;
	@Autowired
	private AlunoRepository ar;

	@Autowired
	private DigitaisRepository dr;
	public int registrarDigital(Digital digital) {

		try {
//			Aluno aluno = ar.findByDigitaisNome(digital.getNome());
//			Frequencia frequencia = new Frequencia();
//			frequencia.setAluno(aluno);
//			frequencia.setRegistro(LocalDateTime.now());
//			// frequencia.setData(new Date());
//			// frequencia.setHora(new Date());
//			fr.save(frequencia);
			dr.save(digital);
			
			if(digital.getId() == 0) {
				throw new Exception();
				
			}
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return 0;
		}

	}
}
