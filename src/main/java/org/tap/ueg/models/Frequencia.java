package org.tap.ueg.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Frequencia implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private long id;
	
	//@Type(type="com.hibernate.samples.type.LocalDateTimeUserType")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	//@Column( columnDefinition = "date")
	private LocalDateTime registro;
	
	@ManyToOne
	@JoinColumn(name="aluno_id")
	@JsonBackReference
	@NotEmpty
	private Aluno aluno;
	@OneToOne
	private Equipamento equipamento;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Equipamento getEquipamento() {
		return equipamento;
	}
	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}
	public LocalDateTime getRegistro() {
		return registro;
	}
	public void setRegistro(LocalDateTime registro) {
		this.registro = registro;
	}

}
