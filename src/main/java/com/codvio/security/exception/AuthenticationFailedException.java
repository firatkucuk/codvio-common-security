
package com.codvio.security.exception;

public class AuthenticationFailedException extends RuntimeException {



    //~ --- [CONSTRUCTORS] ---------------------------------------------------------------------------------------------

    public AuthenticationFailedException(final String message) {

        super(message);
    }
}
