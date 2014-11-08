package br.ufg.inf.redes.interpretador;
import exception.ComandoInvalidoException;
import exception.ValorOperacaoVazio;

public class Interpretador {
	
	private TipoOperacao operacao;
	private String valorOperacao;
	private String parametroOperacao;
	
	private static String SOCKET_RESPONSE_OK = "OK";
	
	public String receberComando(String comando){
		
		// tentamos validar a operacao
		try {
			operacao = recuperarOperacao(comando);
			valorOperacao = recuperarValorOperacao(comando);
			validarOperacao(comando);
			return SOCKET_RESPONSE_OK;
		} catch (ComandoInvalidoException e) {
			return "Comando invalido";
		} catch (ValorOperacaoVazio e) {
			// TODO Auto-generated catch block
			return "Valor da operacao " + operacao + " estava vazio";
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
	 * @param comando
	 *  */
	public TipoOperacao recuperarOperacao(String comando)  throws ComandoInvalidoException{
		String[] argumentos = comando.split(":");
		if (argumentos[0] != null) {
			String operacao = argumentos[0].replace(" ", "_").trim().toUpperCase();
			try {
				validarOperacao(operacao);
				if (comando.matches(TipoOperacao.DATA_REGEX)) { // O comando de data é diferente
					return TipoOperacao.DATA;
				}
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

	public String recuperarValorOperacao(String comando) throws ValorOperacaoVazio{
		String[] argumentos = comando.split(":");
		if (argumentos[1] != null && argumentos[1].length() > 0) {
			return argumentos[1].trim();
		}
		else {
			throw new ValorOperacaoVazio();
		}
	}
	
}
