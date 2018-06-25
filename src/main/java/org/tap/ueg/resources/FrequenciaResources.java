package org.tap.ueg.resources;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.tap.ueg.controller.FrequenciaController;
import org.tap.ueg.models.Aluno;
import org.tap.ueg.models.Digital;
import org.tap.ueg.models.auxi.Registro;

@RestController
@RequestMapping("/frequencia")
public class FrequenciaResources {
	@Autowired
	private FrequenciaController controller;

	@GetMapping(produces = "application/json")
	public @ResponseBody Aluno listas() {
		try {
			return controller.findByMatricula("12015001041");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}
	}

	@GetMapping(value = "/getdata", produces = "application/json")
	public @ResponseBody Date getdata() {
		try {
			// return ar.findByMatricula("12015001041");
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			return df.parse("25/04/2018");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}
	}

	@GetMapping(value = "/listar/{data}", produces = "application/json")
	public @ResponseBody Iterable<Aluno> listaEventos(@PathVariable String date) {
		try {
			//List<Registro> registro = controller.findRegistroByData(date);
			return new ArrayList<Aluno>();
			// return registro;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}

	}

	@GetMapping(value = "/listarRegistro/{data}", produces = "application/json")
	public @ResponseBody List<Registro> listarRegistro(@PathVariable String data) {
		try {

			List<Registro> registro = controller.findRegistroByData(data);

			return registro;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}

	}

	@PostMapping(value = "/registrar", produces = "application/json")
	public @ResponseBody int registrar(@RequestBody Digital digital) {

		try {
			/*
			 * Aluno aluno = ar.findByDigitaisNome(digital.getNome()); Frequencia frequencia
			 * = new Frequencia(); frequencia.setAluno(aluno);
			 * frequencia.setRegistro(LocalDateTime.now()); fr.save(frequencia);
			 */
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return 0;
		}

	}

	@PostMapping(value = "/registrarbydigital/{digital}", produces = "application/json")
	public @ResponseBody int registrarByData(@PathVariable String digital) {

		try {

			return controller.registar(digital);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return 0;
		}

	}

}
