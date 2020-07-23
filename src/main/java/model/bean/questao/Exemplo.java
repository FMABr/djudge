package model.bean.questao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import model.bean.ModelBean;

@Entity
public class Exemplo implements ModelBean<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer num;
	
	@ManyToOne
	@Column(nullable = false)
	private Questao questao;

	@Column(nullable = false)
	private String entrada;
	

	@Column(nullable = false)
	private String saida;
	
	public Exemplo() {
	}
	
	public Exemplo(Integer num, Questao questao, String entrada, String saida) {
		this.num = num;
		this.questao = questao;
		this.entrada = entrada;
		this.saida = saida;
}
	
	public Exemplo(Questao questao, String entrada, String saida) {
			this.questao = questao;
			this.entrada = entrada;
			this.saida = saida;
	}

	public Integer getPK() {
		return num;
	}

	public void setPK(Integer num) {
		this.num = num;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public String getSaida() {
		return saida;
	}

	public void setSaida(String saida) {
		this.saida = saida;
	}
	
	
}
