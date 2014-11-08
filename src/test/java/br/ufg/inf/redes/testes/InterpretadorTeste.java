package br.ufg.inf.redes.testes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.ufg.inf.redes.interpretador.Interpretador;
import br.ufg.inf.redes.interpretador.TipoOperacao;
import exception.ComandoInvalidoException;
import exception.ValorOperacaoVazio;


public class InterpretadorTeste {

	Interpretador interpretador = new Interpretador();
	
	@Test
	public void comandoValidoTest() throws ComandoInvalidoException {
		
		String comandoValido1 = "RCPT_TO";
		String comandoInvalido1 = "RCPT__TO";
		
		// Testes com comandos validos
		try {
			getInterpretador().validarOperacao(comandoValido1);
		} catch (ComandoInvalidoException e) {
			throw new AssertionError();
		}
		
		/** TODO extender para avaliar um array em vez de uma string, permitindo usar
		 * todos os comandos conhecidos e uma lista de comandos inváldiso no teste */
		try {
			getInterpretador().validarOperacao(comandoInvalido1);
			throw new AssertionError("Um comando inválido não foi detectado");
		} catch (ComandoInvalidoException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void extrairOperacaoTest() {
		TipoOperacao operacao;
		try {
			operacao = getInterpretador().recuperarOperacao("RCPT TO: daniel@gmail.com");
			assertTrue(operacao == TipoOperacao.RCPT_TO);
			System.out.println("RCPT TO");
			operacao = getInterpretador().recuperarOperacao("MAIL FROM: daniel@gmail.com");
			assertTrue(operacao == TipoOperacao.MAIL_FROM);
			System.out.println("MAIL FROM");
			operacao = getInterpretador().recuperarOperacao("SUBJECT: Mensagem bacana");
			assertTrue(operacao == TipoOperacao.SUBJECT);
			System.out.println("SUBJECT");
			operacao = getInterpretador().recuperarOperacao("DATA"
					+ "\nOla isso é uma mensagem"
					+ "\nEssa é mais uma linha só pra zuar seu codigo"
					+ "\n."
					+ "\n"
					+ "\n");
			assertTrue(operacao == TipoOperacao.DATA);
			System.out.println("DATA");
		}
		catch (ComandoInvalidoException e) {
			throw new AssertionError("Erro ao tentar extrair operacao de um comando valido", e);
		}
	}
	
	@Test
	public void extrairValorOperacaoTest() {
		String valorOperacao;
		try {
			valorOperacao = getInterpretador().recuperarValorOperacao("RCPT TO: daniel@gmail.com");
			assertTrue(valorOperacao.equals("daniel@gmail.com"));
			
			valorOperacao = getInterpretador().recuperarValorOperacao("MAIL FROM: daniel@gmail.com");
			assertTrue(valorOperacao.equals("daniel@gmail.com"));
			
			valorOperacao = getInterpretador().recuperarValorOperacao("SUBJECT: Mensagem bacana");
			assertTrue(valorOperacao.equals("Mensagem bacana"));
			
			String mensagem = "\nOla isso é uma mensagem"
					+ "\nEssa é mais uma linha só pra zuar seu codigo"
					+ "\n."
					+ "\n"
					+ "\n";
			valorOperacao = getInterpretador().recuperarValorOperacao("DATA"
					+ mensagem);
			
			assertTrue(valorOperacao.equals(mensagem));
			
		}
		catch (ValorOperacaoVazio e) {
			throw new AssertionError("Erro o valor da operacao é nulo ou vazio", e);
		} catch (ComandoInvalidoException e) {
			throw new AssertionError("Comando inválido", e);
		}
	}
	
	@Test
	public void receberComandoValidoTest() {
		String resposta;
		
		resposta = getInterpretador().receberComando("RCPT TO: daniel@gmail.com");
		assertTrue(resposta.equals(Interpretador.SOCKET_RESPONSE_OK));
		
		resposta = getInterpretador().receberComando("MAIL FROM: daniel@gmail.com");
		assertTrue(resposta.equals(Interpretador.SOCKET_RESPONSE_OK));
		
		resposta = getInterpretador().receberComando("SUBJECT: Mensagem bacana");
		assertTrue(resposta.equals(Interpretador.SOCKET_RESPONSE_OK));
		
		String mensagem = "\nOla isso é uma mensagem"
				+ "\nEssa é mais uma linha só pra zuar seu codigo"
				+ "\n."
				+ "\n"
				+ "\n";
		resposta = getInterpretador().receberComando("DATA\n"+mensagem);
		assertTrue(resposta.equals(Interpretador.SOCKET_RESPONSE_OK));
		
	}
	
	
	
	private Interpretador getInterpretador(){
		return new Interpretador();
	}

}
