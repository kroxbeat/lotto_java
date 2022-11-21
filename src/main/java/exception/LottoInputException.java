package exception;

public class LottoInputException extends RuntimeException {
    public LottoInputException() {
    }

    public LottoInputException(String message) {
        super(message);
    }
}
