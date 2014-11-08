package br.ufg.inf.redes.interpretador;
import exception.ComandoInvalidoException;

public class Interpretador {
	
	
	
	public void receberComando(String comando){
		
		
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
			System.out.println("Comando "+ operacao + " é válido");
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
				if (comando.matches(TipoOperacao.DATA_REGEX)) { // O comando de data é diferente
					return TipoOperacao.DATA;
				}
				return TipoOperacao.valueOf(operacao.trim());
			}
			catch (Exception e) {
				throw new ComandoInvalidoException("O comando " + operacao + " não é conhecido ");
			}
		}
		else {
			throw new ComandoInvalidoException("O comando " + comando + " não possui uma sintaxe adequada");
		}
	}
	
}
