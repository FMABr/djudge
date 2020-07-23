package model.bean.questao;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import model.bean.ModelBean;
import model.bean.usuario.Avaliador;

@Entity
public class Questionario implements ModelBean<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Avaliador avaliador;
	
	@OneToMany
	private List<Questao> questoes;

	public Questionario() {
	}

	public Questionario(Long id) {
		this.id = id;
	}

	public Long getPK() {
		return id;
	}

	public void setPK(Long id) {
		this.id = id;
	}

	public Avaliador getAvaliador() {
		return avaliador;
	}

	public void setAvaliador(Avaliador avaliador) {
		this.avaliador = avaliador;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}
	
	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}
	
	public void addQuestao(Questao questao) {
		questoes.add(questao);
	}
	
	public void removeQuestao(Questao questao) {
		questoes.remove(questao);
	}
}
