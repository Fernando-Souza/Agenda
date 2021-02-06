package model.entities;

import java.util.ArrayList;
import java.util.List;

public class Contato implements Comparable<Contato> {

	private String nome;
	private String cargo;
	private int compromissoId;
	private Telefone telefone;
	
	public Contato(String nome, String cargo, Telefone telefone,int compromisso){

		this.nome = nome;
		this.cargo = cargo;
		this.telefone = telefone;
		this.compromissoId = compromisso;

	}
	
		
	public Contato(String nome,Telefone telefone,int compromisso) {

		this.nome = nome;		
		this.telefone = telefone;
		this.compromissoId=compromisso;
	}


	public String getNome() {
		return nome;
	}

	public void addNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void addCargo(String cargo) {
		this.cargo = cargo;
	}

	public Telefone getTelefone() {
		return this.telefone;
	}

	public void setTelefone(Telefone telefone) {
		
		this.telefone= telefone;
	}

		
	public int getCompromisso() {
		
		return this.compromissoId;
		
	}
	
	public void setCompromisso(int id) {
		
		this.compromissoId = id;
	}

	

	@Override
	public String toString() {
		return "Contato [nome=" + nome + ", cargo=" + cargo + ", Cod Area = ( " 
	+ telefone.getCodArea()+" )"+ ", telefone=" + telefone.getNumero()+"]";
	}
 
	@Override
	public int compareTo(Contato other) {
		
		if(this.telefone.equals(other.telefone)) {
			
			return 0;
		}
		
		return 1;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}
	
	

	
}
