 package org.tap.ueg.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Aluno implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	private String matricula;
	
	//@OneToMany(cascade = CascadeType.ALL,mappedBy = "aluno")
	 @OneToMany(mappedBy = "aluno", targetEntity = Digital.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Digital> digitais;
	
	//@OneToMany(cascade = CascadeType.ALL,mappedBy = "aluno")
	 @OneToMany(mappedBy = "aluno", targetEntity = Frequencia.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Frequencia> frequencia;
	
	@OneToOne
	private Pessoa pessoa;
	
	
	public List<Digital> getDigitais() {
		return digitais;
	}
	public void setDigitais(List<Digital> digitais) {
		this.digitais = digitais;
	}
	public List<Frequencia> getFrequencia() {
		return frequencia;
	}
	public void setFrequencia(List<Frequencia> frequencia) {
		this.frequencia = frequencia;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
}
