package org.tap.ueg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.tap.ueg.business.FrequenciaBS;
import org.tap.ueg.models.Aluno;
import org.tap.ueg.models.auxi.Registro;

@Service
public class FrequenciaController {
	@Autowired
	FrequenciaBS bs = new FrequenciaBS();
	public List<Registro> findRegistroByData(String date) {
		// TODO Auto-generated method stub
		return bs.findRegistroByData(date);
	}

	public Aluno findByMatricula(String string) {
		// TODO Auto-generated method stub
		return bs.findByMatricula(string);
	}

	public void registar(String digital) {
		// TODO Auto-generated method stub
		
		
		bs.registar(digital);

	}
	
	//private void 

}
