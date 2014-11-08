package br.ufg.inf.redes.interpretador;
import br.ufg.inf.redes.controle.MontadorEmail;
import exception.ComandoInvalidoException;
import exception.ValorOperacaoVazio;

public class Interpretador {
	
	private TipoOperacao operacao;
	private String valorOperacao;
	private String parametroOperacao;
	
	public static String SOCKET_RESPONSE_OK = "OK";
	
	public String receberComando(String comando){
		
		// tentamos validar a operacao
		try {
			operacao = recuperarOperacao(comando);
			valorOperacao = recuperarValorOperacao(comando);
			MontadorEmail montadorEmail = new MontadorEmail();
			montadorEmail.receberConteudo(operacao.valorOriginal(), valorOperacao);
			return SOCKET_RESPONSE_OK;
		} catch (ComandoInvalidoException e) {
			return "Comando invalido";
		} catch (ValorOperacaoVazio e) {
			// TODO Auto-generated catch block
			return "Valor da operacao " + operacao + " estava vazio";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "Erro desconhecido. A montagem do e-mail possivelmente falhou.";
		}
		
	}
	
	/** valida o a operação tentaada no comando
	 *  metodo silencioso em caso de sucesso. Dispara uma excecao em caso de comando inválido
	 *  @param operacao String operacao extraída do comando bruto
	 *  @return void
	 *  @throws ComandoInvalidoException se o comando for desconhecido
	 *  */
	public void validarOperacao(String operacao) throws ComandoInvalidoException {
		operacao = operacao.replace(" ", "_");
		try {
			TipoOperacao.valueOf(operacao) ;
		} catch (Exception e) {
			System.out.println("Comando "+ operacao + " é inválido");
			throw new ComandoInvalidoException(e);
		}
		
	}

	/** Com base em um comando bruto, tenta retornar o {@link TipoOperacao} correspondente
	 * @param comando String comando bruto enviado pelo socket
	 * @return {@link TipoOperacao} Tipo de operação realizada
	 *  */
	public TipoOperacao recuperarOperacao(String comando)  throws ComandoInvalidoException{
		if (comando.startsWith(TipoOperacao.DATA_STARTS_WITH)) { // O comando de data é diferente
			return TipoOperacao.DATA;
		}
		
		String[] argumentos = comando.split(":");
		if (argumentos[0] != null) {
			String operacao = argumentos[0].replace(" ", "_").trim().toUpperCase();
			try {
				validarOperacao(operacao);
				return TipoOperacao.valueOf(operacao);
			}
			catch (Exception e) {
				throw new ComandoInvalidoException("O comando " + operacao + " não é conhecido ");
			}
		}
		else {
			throw new ComandoInvalidoException("O comando " + comando + " não possui uma sintaxe adequada");
		}
	}

	/** Recupera o valor da operacao tentada pelo comando 
	 * @param comando String comando bruto enviado pelo socket
	 * @return String valor da operação, para montagem da mensagem 
	 * 	 * 
	 * @throws ComandoInvalidoException, ValorOperacaoVazio */
	public String recuperarValorOperacao(String comando) throws ValorOperacaoVazio, ComandoInvalidoException{
		//tratamento para o DATA
		if (operacao == null) {
			operacao = recuperarOperacao(comando);
		}
		
		String[] argumentos = new String[2];
		
		if (operacao == TipoOperacao.DATA) {
			argumentos[0] = "DATA";
			argumentos[1] = comando.replaceFirst(TipoOperacao.DATA_STARTS_WITH, "");
		}
		else {
			argumentos = comando.split(":");
		}
		
		if (argumentos[1] != null && argumentos[1].length() > 0) {
			if (operacao ==  TipoOperacao.DATA) {
				return argumentos[1];
			}
			return argumentos[1].trim();
		}
		else {
			throw new ValorOperacaoVazio();
		}
	}
	
}
