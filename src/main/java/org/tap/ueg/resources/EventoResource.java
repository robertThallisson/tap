package org.tap.ueg.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.tap.ueg.models.Evento;
import org.tap.ueg.repository.EventoRepository;

@RestController
@RequestMapping("/evento")
public class EventoResource {

	@Autowired
	private EventoRepository er;

	@GetMapping(produces="application/json")
	public @ResponseBody  String listaEventos(){
	
		return "E tudo boqueta";
	}
	
	
	@GetMapping(value="/{codigo}", produces="application/json")
	public @ResponseBody Evento evento(@PathVariable(value="codigo") long codigo){
		
		return new Evento();
	}
	

	@PostMapping()
	public Evento cadastraEvento(@RequestBody @Valid Evento evento){
		er.save(evento);
		//long codigo = evento.getCodigo();
		return evento;
	}
	
	
	@DeleteMapping()
	public Evento deletaEvento(@RequestBody Evento evento){
		er.delete(evento);
		return evento;
	}

}
