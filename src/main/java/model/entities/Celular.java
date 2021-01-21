package model.entities;

public class Celular extends Telefone {

		
	public Celular(String operadora,String codArea,String numero) {
		super(operadora, codArea);
		setNumber(numero);
		super.setTipo(TipoTel.CELULAR);
	}
	
	public Celular(String codArea, String numero) {

		super(codArea);
		this.setNumber(numero);
		super.setTipo(TipoTel.CELULAR);

	}

	
	@Override
	public void setNumber(String numero) {

		//String numeroformat = new StringBuffer(numero).insert(numero.length()-4,"-").toString();
		
				
		String Expressao = "^[9]{1}[6-9]{1}[0-9]{3}[0-9]{4}$";

				
		if (numero.matches(Expressao)) {

			super.setNumber(numero);
			
		}else {
			
        
		super.setNumber("12");
		
		}

	}

	

}
