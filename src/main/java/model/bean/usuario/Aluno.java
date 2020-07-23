package model.bean.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import model.bean.ModelBean;

@Entity
public class Aluno extends Usuario implements ModelBean<Long>{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(unique = true, nullable = false)
	private String matricula;
	
	@ManyToOne
	private Turma turma;

	public Aluno() {
	}

	public Aluno(Long id, String matricula, String email, String nome, String senha, Turma turma) {
		super(email, nome, senha);
		this.id = id;
		this.matricula = matricula;
		this.turma = turma;
	}
	
	public Aluno(Long id, String matricula, String email, String nome, String senha) {
		super(email, nome, senha);
		this.id = id;
		this.matricula = matricula;
	}
	
	public Aluno(String matricula, String email, String nome, String senha, Turma turma) {
		super(email, nome, senha);
		this.matricula = matricula;
		this.turma = turma;
	}
	
	public Aluno(String matricula, String email, String nome, String senha) {
		super(email, nome, senha);
		this.matricula = matricula;
	}

	public Long getPK() {
		return id;
	}

	public void setPK(Long id) {
		this.id = id;
	}
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
}
