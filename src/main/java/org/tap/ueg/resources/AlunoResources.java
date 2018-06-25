package org.tap.ueg.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.tap.ueg.controller.AlunoController;
import org.tap.ueg.models.Aluno;
import org.tap.ueg.models.Cidade;
import org.tap.ueg.models.Digital;
import org.tap.ueg.models.Pessoa;
import org.tap.ueg.repository.AlunoRepository;
import org.tap.ueg.repository.CidadeRepository;
import org.tap.ueg.repository.DigitaisRepository;
import org.tap.ueg.repository.PessoaRepository;


@RestController
@RequestMapping("/aluno")
public class AlunoResources {
	@Autowired
	private AlunoRepository ar;
	@Autowired
	private PessoaRepository pr;
	@Autowired
	private CidadeRepository cr;

	@Autowired
	private DigitaisRepository dr;
	@Autowired
	AlunoController alunoController ;

	@GetMapping(produces = "application/json")
	public @ResponseBody Aluno getModeloAluno() {
		Aluno aluno = new Aluno();
		aluno.setMatricula("12015001045");
		aluno.setPessoa(new Pessoa());
		aluno.getPessoa().setCep("122323");
		aluno.getPessoa().setCpf("05534709146");
		aluno.getPessoa().setCidade(new Cidade());
		aluno.getPessoa().getCidade().setNome("goanesia");
		return aluno;
	}

	
	

	
	@GetMapping(value="/getDigital",produces = "application/json")
	public @ResponseBody Digital getDigital() {
		Digital digital = new Digital();
		digital.setAluno(new Aluno());
		digital.setNome("123456");
		digital.getAluno().setMatricula("12015001041");
		return digital;
	}
	@GetMapping(value="/getAlunosByMatricula",produces = "application/json")
	public @ResponseBody Aluno getAlunoByMatricula(@PathVariable String  matricula) {
		return ar.findByMatricula(matricula); 
	}
	@GetMapping(value="/getAlunos",produces = "application/json")
	public @ResponseBody Iterable<Aluno> getAllAlunos() {
		return ar.findAll(); 
	}
	
	@PostMapping(produces="application/json")
	public @ResponseBody  int registar(@RequestBody Aluno aluno){
		
		try {
			//AlunoController
			
			cr.save(aluno.getPessoa().getCidade());
			pr.save(aluno.getPessoa());
			ar.save(aluno);
			return 1;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return 0;
		}
		
	}

	@PostMapping(value="/registarDigital",produces="application/json")
	public @ResponseBody  int registrarDigital(@RequestBody Digital digital){
		
		try {
			digital.setAluno(ar.findByMatricula(digital.getAluno().getMatricula()));
			dr.save(digital);
			return 1;
		}catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		
	}
	
	
	@PostMapping(value="/registarDigitalByString/{matricula}",produces="application/json")
	public @ResponseBody  int registrarDigitalByString(@PathVariable String  matricula){
		
		try {
			//digital.setAluno(ar.findByMatricula(digital.getAluno().getMatricula()));
			//dr.save(digital);]
			
			return alunoController.registrarDigitalByString(matricula);
			
			
		}catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		
	}
	
	@GetMapping(value="/consultarRegistro/{matricula}",produces="application/json")
	public @ResponseBody  int consultarRegistro(@PathVariable String  matricula){
		
		try {
			//digital.setAluno(ar.findByMatricula(digital.getAluno().getMatricula()));
			//dr.save(digital);
			Digital digital = new Digital();
			digital.setNome(matricula.split(";")[1]);
			digital.setAluno(new Aluno());
			digital.getAluno().setMatricula(matricula.split(";")[0]);
			
			
			if (ar.findByDigitaisNome(matricula.split(";")[1]) != null) {
				return 1;
			}else {
				return 0;	
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		
	}

}
