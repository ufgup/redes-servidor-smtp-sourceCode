package main.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import br.ufg.inf.redes.controle.Reencaminhador;
import br.ufg.inf.redes.entidades.Email;

public class ReencaminhadorTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void reencaminharTest() {
		Reencaminhador reencaminhador = new Reencaminhador();
		Email email = new Email();
		email.setRemetente("danielmelogpi@gmail.com");
		email.setDestinatario("danielmelogpi@gmail.com");
		email.setAssunto("Assunto do email" + Math.random());
		email.setMensagem("Minha mensagem " + (Math.PI * Math.random()));
		boolean envio = reencaminhador.encaminharParaOutroSMTP(email);
		assertTrue(envio);
	}

}
