package order.net.class101.server1.exception;


public class SoldOutException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SoldOutException(String message) {
        super(message);
    }

    public SoldOutException(String message, Exception exception) {
        super(message, exception);
    }
}
