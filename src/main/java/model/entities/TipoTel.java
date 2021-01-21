package model.entities;

public enum TipoTel {
	
	FIXO (1),
	CELULAR(2);
	
	private final int id;
	
	TipoTel(int id){
		
		this.id = id;
		
	}
	
	public int getID() {
		
		return this.id;
	}

}
