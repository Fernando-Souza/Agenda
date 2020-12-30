package model.entities;

public abstract class Telefone {

	private String operadora;
	private String codArea;
	private String numero;
	private String tipo;

	public Telefone(String operadora, String tipo, String codArea) {

		this.operadora = operadora;
		this.tipo = tipo;

		validaCodArea(codArea);

	}

	public Telefone(String tipo, String codArea) {

		this.codArea = codArea;

	}

	public String getOperadora() {
		return operadora;
	}

	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}

	public String getCodArea() {
		return codArea;
	}

	public void setCodArea(String codArea) {
		this.codArea = codArea;
	}

	public String getNumero() {

		return numero;
	}

	public void setNumber(String numero) {

		this.numero = numero;

	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void validaCodArea(String codArea) {

		String areaExpressao = "^[1-9]{2}$";

		if (codArea.matches(areaExpressao)) {

			this.codArea = codArea;
		} else {

			System.out.println("codigo de área incorreto!!");
			this.codArea = null;

		}

	}

	@Override
	public String toString() {

		return "(" + codArea + ") " + numero;
	}

}
