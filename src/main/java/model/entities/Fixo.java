package model.entities;

public class Fixo extends Telefone {

	public Fixo(String operadora, String codArea, String numero) {

		super(operadora, codArea);
		this.setNumber(numero);
		super.setTipo(TipoTel.FIXO);

	}

	public Fixo(String codArea, String numero) {

		super(codArea);
		this.setNumber(numero);
		super.setTipo(TipoTel.FIXO);

	}

	@Override
	public void setNumber(String numero) {

		//String numeroformat = new StringBuffer(numero).insert(numero.length() - 4, "-").toString();

		String expressao = "^[2-5]{1}[0-9]{3}[0-9]{4}$";

		if (numero.matches(expressao)) {

			super.setNumber(numero);

		} else {

			super.setNumber(null);

		}

	}

}
