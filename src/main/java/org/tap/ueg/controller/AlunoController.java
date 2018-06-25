package org.tap.ueg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tap.ueg.business.AlunoBS;
import org.tap.ueg.models.Digital;
import org.tap.ueg.repository.AlunoRepository;

@Service
public class AlunoController {
	@Autowired
	private AlunoRepository ar;
	@Autowired
	private AlunoBS alunoBS;

	public int registrarDigitalByString(String matricula) throws Exception {
		// TODO Auto-generated method stub
		try {
			Digital digital = new Digital();
			digital.setNome(matricula.split("-")[1]);
			// digital.setAluno(new Aluno());
			// digital.getAluno().setMatricula(matricula.split("-")[0]);
			digital.setAluno(ar.findByMatricula(matricula.split("-")[0]));

			if (digital.getAluno() == null) {
				return 0;
			}

			alunoBS.registrarDigital(digital);
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

}
