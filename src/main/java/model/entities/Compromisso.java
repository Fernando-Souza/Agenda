package model.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Compromisso {

	String titulo;
	LocalDate  dataEvento;
	LocalTime horario;
	String endereco;
	String referencia;	
	String descricao;
	
	DateTimeFormatter formatDia = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	DateTimeFormatter formatHora = DateTimeFormatter.ofPattern("HH:mm:ss");

	public Compromisso(String titulo, String data, String horario, String endereco, String referencia,
			String descricao) {
		
		this.titulo=titulo;
		this.dataEvento = LocalDate.parse(data,formatDia);
		this.horario=LocalTime.parse(horario,formatHora);
		this.endereco=endereco;
		this.referencia=referencia;		
		this.descricao=descricao;

	}

	public Compromisso(String titulo, String data, String horario, String endereco,String descricao) {

		this.titulo=titulo;
		this.dataEvento = LocalDate.parse(data);
		this.horario=LocalTime.parse(horario);
		this.endereco=endereco;		
		this.descricao=descricao;	
		
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
		
	public long diasParaEvento() {
		
	return ChronoUnit.DAYS.between(LocalDate.now(), dataEvento);
	
	
	}

	@Override
	public String toString() {
		return "Compromisso [titulo=" + titulo + ", dataEvento=" + dataEvento + ", horario=" + horario
				+ ", endereco=" + endereco + ", referencia=" + referencia + ", descricao=" + descricao + "]";
	}
	
	
	
	

}
