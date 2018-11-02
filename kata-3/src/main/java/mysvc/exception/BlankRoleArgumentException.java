package mysvc.exception;

/**
 * Created by jyustiz on 11/1/18 for project mysvc.
 */
public class BlankRoleArgumentException extends IllegalArgumentException {
    private static final long serialVersionUID = -3456841003433599168L;

    public BlankRoleArgumentException() {
    }

    public BlankRoleArgumentException(String message) {
        super(message);
    }

    public BlankRoleArgumentException(Throwable cause) {
        super(cause);
    }

}
