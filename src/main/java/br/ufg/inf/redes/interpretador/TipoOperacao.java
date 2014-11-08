package br.ufg.inf.redes.interpretador;

public enum TipoOperacao {
	
	EHLO(0),
	HELO(1), 
	RCPT_TO(2), 
	MAIL_FROM(3),
	SUBJECT(4), 
	DATA(5);
	
	public static String DATA_STARTS_WITH = "DATA";
	
	private TipoOperacao(int tipo) {
	}
	
	private TipoOperacao(String tipo) {
	}

	public String operacaoOriginal() {
		return this.toString().replace("_", " ");
	}
}
