
package io.codv.springwind.service;

import io.codv.springwind.dto.vo.AuthenticatedUserInfo;
import io.codv.springwind.singleton.InMemoryTokenDbSingleton;



public abstract class InMemoryAuthenticationService implements AuthenticationService {



    //~ --- [INSTANCE FIELDS] ------------------------------------------------------------------------------------------

    private InMemoryTokenDbSingleton tokenDbSingleton = InMemoryTokenDbSingleton.getInstance();



    //~ --- [METHODS] --------------------------------------------------------------------------------------------------

    @Override
    public void cleanExpiredSessions() {

        tokenDbSingleton.deleteExpiredTokens();
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
}
