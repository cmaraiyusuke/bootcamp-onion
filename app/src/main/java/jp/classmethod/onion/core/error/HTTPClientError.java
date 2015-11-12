package jp.classmethod.onion.core.error;

/**
 * HTTPクライアントで発生するエラー
 */
public class HTTPClientError extends RuntimeException {

    public HTTPClientError(Throwable throwable) {
        super(throwable);
    }

    @Override
    public String getMessage() {
        return "HTTPClientError: " + super.getMessage();
    }

}
