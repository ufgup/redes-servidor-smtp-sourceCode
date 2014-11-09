package br.ufg.inf.redes.controle;

import java.io.IOException;

import br.ufg.inf.redes.entidades.Email;
import br.ufg.inf.redes.persistencia.GeradorProperties;
import exception.ComandoInvalidoException;
import exception.FalhaEncaminhamentoException;

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

				GeradorProperties gp = new GeradorProperties();
				gp.gravarLocalSender(mail);

				if( mail.identificarDominio( mail.getDestinatario() ).equals("localhost") ) {
					gp.gravarLocalReceiver(mail);
				}
				else {
					Reencaminhador re = new Reencaminhador();
					re.encaminharParaOutroSMTP(mail);
				}
			}
			break;
		default:
			throw new ComandoInvalidoException();
		}

	}

}
