
package com.codvio.security.service;

import com.codvio.security.dto.vo.AuthenticatedUserInfo;
import com.codvio.security.singleton.InMemoryTokenDbSingleton;



public abstract class InMemoryAuthenticationService implements AuthenticationService {



    //~ --- [INSTANCE FIELDS] ------------------------------------------------------------------------------------------

    private InMemoryTokenDbSingleton tokenDbSingleton = InMemoryTokenDbSingleton.getInstance();



    //~ --- [METHODS] --------------------------------------------------------------------------------------------------

    @Override
    public void cleanExpiredSessions(final int timeout) {

        tokenDbSingleton.deleteExpiredTokens(timeout);
    }



    //~ ----------------------------------------------------------------------------------------------------------------

    @Override
    public String createSession(final AuthenticatedUserInfo userInfo) {

        return tokenDbSingleton.createNewToken(userInfo);
    }



    //~ ----------------------------------------------------------------------------------------------------------------

    @Override
    public AuthenticatedUserInfo findByToken(final String token) {

        return tokenDbSingleton.findUser(token);
    }



    //~ ----------------------------------------------------------------------------------------------------------------

    @Override
    public void updateSession(final String token, final AuthenticatedUserInfo userInfo) {

        tokenDbSingleton.updateSession(token, userInfo);
    }
}
