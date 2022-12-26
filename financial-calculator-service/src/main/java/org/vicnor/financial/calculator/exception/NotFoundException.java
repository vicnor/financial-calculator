package org.vicnor.financial.calculator.exception;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5162710183389028792L;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String s) {
        super(s);
    }

}
