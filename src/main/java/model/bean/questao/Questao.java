package model.bean.questao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import model.bean.ModelBean;
import model.bean.usuario.Avaliador;

@Entity
public class Questao implements ModelBean<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String titulo;

	@Column(nullable = false)
	@Lob
	private String enunciado;

	@Lob
	private String entrada;

	@Lob
	private String saida;
	
	public String getRestricoes() {
		return restricoes;
	}

	public void setRestricoes(String restricoes) {
		this.restricoes = restricoes;
	}

	public List<Exemplo> getExemplos() {
		return exemplos;
	}

	public void setExemplos(List<Exemplo> exemplos) {
		this.exemplos = exemplos;
	}

	public void setTempoExecucao(Double tempoExecucao) {
		this.tempoExecucao = tempoExecucao;
	}

	private String restricoes;

	@Column(nullable = false)
	private char nivel;

	@Column(nullable = false)
	private int peso;

	@Column(nullable = false)
	@Lob
	private String gabarito;

	private Double tempoExecucao;

	@ManyToOne
	@NotNull
	private Avaliador avaliador;

	@OneToMany
	private List<Exemplo> exemplos;
	
	public Questao() {
	}

	public Questao(long id, String titulo, String enunciado, String entrada, String saida, char nivel, int peso,
			String gabarito, double tempoExecucao, Avaliador avaliador, String restricoes) {
		this.id = id;
		this.titulo = titulo;
		this.enunciado = enunciado;
		this.entrada = entrada;
		this.saida = saida;
		this.nivel = nivel;
		this.peso = peso;
		this.gabarito = gabarito;
		this.tempoExecucao = tempoExecucao;
		this.avaliador = avaliador;
		this.restricoes = restricoes;
	}

	public Questao(String titulo, String enunciado, String entrada, String saida, char nivel, int peso,
			String gabarito, double tempoExecucao, Avaliador avaliador, String restricoes) {
		this.titulo = titulo;
		this.enunciado = enunciado;
		this.entrada = entrada;
		this.saida = saida;
		this.nivel = nivel;
		this.peso = peso;
		this.gabarito = gabarito;
		this.tempoExecucao = tempoExecucao;
		this.avaliador = avaliador;
		this.restricoes = restricoes;
	}

	public Long getPK() {
		return id;
	}

	public void setPK(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
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

	public char getNivel() {
		return nivel;
	}

	public void setNivel(char nivel) {
		this.nivel = nivel;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public String getGabarito() {
		return gabarito;
	}

	public void setGabarito(String gabarito) {
		this.gabarito = gabarito;
	}

	public double getTempoExecucao() {
		return tempoExecucao;
	}

	public void setTempoExecucao(double tempoExecucao) {
		this.tempoExecucao = tempoExecucao;
	}

	public Avaliador getAvaliador() {
		return avaliador;
	}

	public void setAvaliador(Avaliador avaliador) {
		this.avaliador = avaliador;
	}

}
