package org.tap.ueg.models.auxi;

import java.util.List;

public class Registro {
	private String nome;
	private String matricula;
	
	private List<String>  frequencias;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public List<String> getFrequencias() {
		return frequencias;
	}

	public void setFrequencias(List<String> frequencias) {
		this.frequencias = frequencias;
	}
	
}

