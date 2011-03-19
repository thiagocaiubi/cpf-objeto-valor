package exception;

public class InvalidCPFException extends RuntimeException {

	private static final long serialVersionUID = 5551084418542555151L;

	public InvalidCPFException(String cpf) {
		super(String.format("CPF '%s' is invalid", cpf));
	}
}