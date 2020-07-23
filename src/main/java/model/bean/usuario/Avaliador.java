package model.bean.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import model.bean.ModelBean;

@Entity
public class Avaliador extends Usuario implements ModelBean<Long>{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(unique = true, nullable = false)
	private String siape;

	public Avaliador() {
	}

	public Avaliador(Long id, String siape, String email, String nome, String senha) {
		super(email, nome, senha);
		this.id = id;
		this.siape = siape;
	}

	public Avaliador(String siape, String email, String nome, String senha) {
		super(email, nome, senha);
		this.siape = siape;
	}
	
	public Long getPK() {
		return id;
	}

	public void setPK(Long id) {
		this.id = id;
	}

	public String getSiape() {
		return siape;
	}

	public void setSiape(String siape) {
		this.siape = siape;
	}

}
