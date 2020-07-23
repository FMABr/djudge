package model.bean.usuario;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class Usuario {

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String senha;

	public Usuario() {
	}

	public Usuario(String email, String nome, String senha) {
		this.email = email;
		this.nome = nome;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
