package com.dotcom.aurora.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="AU_TURMA")
public class Turma implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String nome;
	private String ano;
	private String periodo;
	private String professor;
	@ManyToOne
	private Escola escola;	
	@OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
	private List<Aluno> aluno;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	public Escola getEscola() {
		return escola;
	}
	public void setEscola(Escola escola) {
		this.escola = escola;
	}
	public List<Aluno> getAluno() {
		return aluno;
	}
	public void setAluno(List<Aluno> aluno) {
		this.aluno = aluno;
	}


	
}
