package exception;

public class FalhaEncaminhamentoException extends Exception {

	private static final long serialVersionUID = -3685369976731926972L;

	private Exception cause;

	public FalhaEncaminhamentoException(Exception e) {
		cause = e;
	}

	public FalhaEncaminhamentoException(String string) {
		cause = new Exception(string);
	}

	public FalhaEncaminhamentoException() {

	}

	@Override
	public synchronized Throwable getCause() {
		// TODO Auto-generated method stub
		return cause;
	}

	@Override
	public String getMessage() {
		return "Falha ao reencaminhar mensagem";
	}
}
