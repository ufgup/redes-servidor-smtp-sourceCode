package br.ufg.inf.redes.testes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.ufg.inf.redes.controle.Reencaminhador;
import br.ufg.inf.redes.entidades.Email;

public class ReencaminhadorTest {

	@Test
	public void reencaminharTest() throws Exception {
		Reencaminhador reencaminhador = new Reencaminhador();
		Email email = new Email();
		email.setRemetente("testedeenvioemail@yahoo.com");
		email.setDestinatario("danielmelogpi@gmail.com");
		email.setAssunto("Assunto do email" + Math.random());
		email.setMensagem("Minha mensagem " + getLoremIpsum());
		reencaminhador.encaminharParaOutroSMTP(email);
	}

	private String getLoremIpsum() {
		return "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
				+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco "
				+ "laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in "
				+ "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non"
				+ " proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
	}

}
