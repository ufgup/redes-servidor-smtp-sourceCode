package br.ufg.inf.redes.controle;

import br.ufg.inf.redes.entidades.Email;
import br.ufg.inf.redes.persistencia.GeradorProperties;

public class MontadorEmail {

	private Email mail;

	public MontadorEmail() {
		mail = new Email();
	}

	public void receberConteudo(String comando, String argumento) throws Exception {

		switch (comando) {
		case "RCPT TO":
			mail.setDestinatario(argumento);
			break;
		case "MAIL FROM":
			mail.setRemetente(argumento);
			break;
		case "DATA":
			mail.setAssunto(argumento);
			break;
		case "SUBJECT":
			mail.setMensagem(argumento);
			break;
		case "SEND":
			if( mail.estaProntoParaEnviar() ) {
				if( mail.identificarDominio( mail.getDestinatario() ) ) {
					GeradorProperties gp = new GeradorProperties();
					gp.gravarLocal(mail);
				}
				else {
					// aqui vai reencaminhar para o proximo servidor SMTP.
				}
			}
			break;
		default:
			throw new Exception("Comando desconhecido!");
		}

	}

}
