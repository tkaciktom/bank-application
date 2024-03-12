package cz.strancice.ttkacik.productbundlemanagementservice.domain.exception;

public class BundleNotFoundException extends RuntimeException {
    public BundleNotFoundException(String message) {
        super(message);
    }
}
