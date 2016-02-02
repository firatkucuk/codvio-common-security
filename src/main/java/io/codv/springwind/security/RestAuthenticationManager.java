package io.codv.springwind.security;

import io.codv.springwind.dto.vo.AuthenticatedUserInfo;
import io.codv.springwind.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;



public class RestAuthenticationManager implements AuthenticationManager {



    //~ --- [STATIC FIELDS/INITIALIZERS] -------------------------------------------------------------------------------

    private static final Logger LOG = LoggerFactory.getLogger(RestAuthenticationManager.class);



    //~ --- [INSTANCE FIELDS] ------------------------------------------------------------------------------------------

    private final AuthenticationService authenticationService;



    //~ --- [CONSTRUCTORS] ---------------------------------------------------------------------------------------------

    public RestAuthenticationManager(final AuthenticationService authenticationService) {

        this.authenticationService = authenticationService;
    }



    //~ --- [METHODS] --------------------------------------------------------------------------------------------------

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

        LOG.debug("Authenticating credentials ...");
        LOG.trace("cleaning expired sessions ...");

        authenticationService.cleanExpiredSessions();

        final StatelessAuthenticationToken authenticationToken = (StatelessAuthenticationToken) authentication;
        final String                       token               = authenticationToken.getToken();
        final AuthenticatedUserInfo        userInfo            = authenticationService.findByToken(token);

        if (userInfo != null) {
            LOG.trace("Matched user found!");

            authenticationToken.setAuthenticatedUserInfo(userInfo);

            return authenticationToken;
        } else {
            LOG.trace("Customer matched with token couldn't found!");
        }

        return null;
    }
}
