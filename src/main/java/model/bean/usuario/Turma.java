package model.bean.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import model.bean.ModelBean;
import model.bean.usuario.Aluno;

@Entity
public class Turma implements ModelBean<Long>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String nome;
	
	@OneToMany
	private List<Aluno> alunos;
	
	public Turma() {
	}
	
	public Turma(Long id, String nome, List<Aluno> alunos) {
		this.id = id;
		this.nome = nome;
		this.alunos = alunos;
	}
	
	public Turma(String nome, List<Aluno> alunos) {
		this.nome = nome;
		this.alunos = alunos;
	}
	
	public Turma(String nome) {
		this.nome = nome;
		this.alunos = new ArrayList<>();
	}
	
	public Long getPK() {
		return id;
	}
	
	public void setPK(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Aluno> getAlunos() {
		return alunos;
	}
	
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	public void addAluno(Aluno aluno) {
		alunos.add(aluno);
	}
	
	public void removeAluno(Aluno aluno) {
		alunos.remove(aluno);
	}
}
