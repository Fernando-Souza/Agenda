package model.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Compromisso {

	String titulo;
	LocalDate  dataEvento;
	LocalTime horario;
	String endereco;
	String referencia;
	List<Contato> contato;
	String descricao;

	public Compromisso(String titulo, String data, String horario, String endereco, String referencia, Contato contato,
			String descricao) {
		
		this.titulo=titulo;
		this.dataEvento = LocalDate.parse(data);
		this.horario=LocalTime.parse(horario);
		this.endereco=endereco;
		this.referencia=referencia;
		this.contato.add(contato);
		this.descricao=descricao;

	}

	public Compromisso(String titulo, String data, String horario, String endereco, Contato contato, String descricao) {

		this.titulo=titulo;
		this.dataEvento = LocalDate.parse(data);
		this.horario=LocalTime.parse(horario);
		this.endereco=endereco;		
		this.contato.add(contato);
		this.descricao=descricao;	
		
	}

	public Compromisso(String titulo, String data, String horario, String endereco, Contato contato) {
		
		this.titulo=titulo;
		this.dataEvento = LocalDate.parse(data);
		this.horario=LocalTime.parse(horario);
		this.endereco=endereco;		
		this.contato.add(contato);
		
	}
	
	public Compromisso(String titulo, String data, String horario, String endereco) {

		this.titulo=titulo;
		this.dataEvento = LocalDate.parse(data);
		this.horario=LocalTime.parse(horario);
		this.endereco=endereco;		
		
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDate getData() {
		return dataEvento;
	}

	public void setData(LocalDate data) {
		this.dataEvento = data;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public List<Contato> getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		
		if(!this.contato.contains(contato)) {
			
		this.contato.add(contato);
		
		}else {
			System.out.println("Contato já cadastrado!!");
		}
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public long diasParaEvento() {
		
	return ChronoUnit.DAYS.between(LocalDate.now(), dataEvento);
	
	
	}
	
	
	
	

}
