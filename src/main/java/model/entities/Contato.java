package model.entities;

import java.util.ArrayList;
import java.util.List;

public class Contato implements Comparable<Contato> {

	private String nome;
	private String cargo;
	private List<Telefone> telefone = new ArrayList<>();
	private List<Email> email = new ArrayList<>();

	public Contato(String nome, String cargo, Telefone telefone, Email email) {

		this.nome = nome;
		this.cargo = cargo;
		this.telefone.add(telefone);
		this.email.add(email);

	}
	
	public Contato(String nome, String cargo, Telefone telefone) {

		this.nome = nome;
		this.cargo = cargo;
		this.telefone.add(telefone);
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public List<Telefone> getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		
		this.telefone.add(telefone);
	}

	public List<Email> getEmail() {
		return email;
	}

	public void setEmail(List<Email> email) {
		this.email = email;
	}

	

	@Override
	public String toString() {
		return "Contato [nome=" + nome + ", cargo=" + cargo + ", telefone=" 
	+ telefone.get(0).toString() + ", email=" + email.get(0) + "]";
	}
 
	@Override
	public int compareTo(Contato other) {
		
		if(this.telefone.contains(other.telefone)) {
			
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
