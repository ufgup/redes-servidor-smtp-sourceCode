package main.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.ufg.inf.redes.interpretador.Interpretador;
import br.ufg.inf.redes.interpretador.TipoOperacao;
import exception.ComandoInvalidoException;


public class InterpretadorTeste {

	Interpretador interpretador = new Interpretador();
	
	@Test
	public void comandoValidoTest() throws ComandoInvalidoException {
		
		String comandoValido1 = "RCPT_TO";
		String comandoInvalido1 = "RCPT__TO";
		
		// Testes com comandos validos
		try {
			interpretador.validarOperacao(comandoValido1);
		} catch (ComandoInvalidoException e) {
			throw new AssertionError();
		}
		
		/** TODO extender para avaliar um array em vez de uma string, permitindo usar
		 * todos os comandos conhecidos e uma lista de comandos inváldiso no teste */
		try {
			interpretador.validarOperacao(comandoInvalido1);
			throw new AssertionError("Um comando inválido não foi pego");
		} catch (ComandoInvalidoException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void extrairOperacaoTest() {
		TipoOperacao operacao;
		try {
			operacao = interpretador.recuperarOperacao("RCPT TO: daniel@gmail.com");
			assertTrue(operacao == TipoOperacao.RCPT_TO);
			System.out.println("RCPT TO");
			operacao = interpretador.recuperarOperacao("MAIL FROM: daniel@gmail.com");
			assertTrue(operacao == TipoOperacao.MAIL_FROM);
			System.out.println("MAIL FROM");
			operacao = interpretador.recuperarOperacao("SUBJECT: Mensagem bacana");
			assertTrue(operacao == TipoOperacao.SUBJECT);
			System.out.println("SUBJECT");
			operacao = interpretador.recuperarOperacao("DATA"
					+ "Ola isso é uma mensagem"
					+ "Essa é mais uma linha só pra zuar seu codigo"
					+ "."
					+ ""
					+ "");
			assertTrue(operacao == TipoOperacao.DATA);
			System.out.println("DATA");
		}
		catch (ComandoInvalidoException e) {
			throw new AssertionError("Erro ao tentar extrair operacao de um comando valido", e);
		}
	}

}
