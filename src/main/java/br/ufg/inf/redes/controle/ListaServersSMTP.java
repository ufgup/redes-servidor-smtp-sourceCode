package br.ufg.inf.redes.controle;

public enum ListaServersSMTP {

	R2(		"grupo8-r2.inf.ufg.br", 	"r2.grupo8.inf.ufg.br"),
	MV3(	"grupo8-mv3.inf.ufg.br",	"mv3.grupo8.inf.ufg.br");

	private final String dominio;
	private final String host;

	private ListaServersSMTP(String dominio, String host){
		this.dominio = dominio;
		this.host = host;
	}

	public String getHost() {
		return host;
	}

}
