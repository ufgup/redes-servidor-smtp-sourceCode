package exception;

public class ComandoInvalidoException extends Exception {

	private static final long serialVersionUID = -581575983197227788L;
	private Exception cause;
	public ComandoInvalidoException(Exception e) {
		cause = e;
	}

	public ComandoInvalidoException() {
		// TODO Auto-generated constructor stub
	}

	public ComandoInvalidoException(String string) {
		cause = new Exception(string);
	}
	@Override
	public synchronized Throwable getCause() {
		// TODO Auto-generated method stub
		return cause;
	}

	@Override
	public String getMessage() {
		return "O comando é inválido";
	}
	
}
