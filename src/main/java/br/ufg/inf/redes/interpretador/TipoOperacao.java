package br.ufg.inf.redes.interpretador;

public enum TipoOperacao {
	
	RCPT_TO(1), MAIL_FROM(2),
	SUBJECT(3), DATA(4);
	
	public static String DATA_REGEX = "^DATA.*";
	
	private TipoOperacao(int tipo) {
		
	}
}
