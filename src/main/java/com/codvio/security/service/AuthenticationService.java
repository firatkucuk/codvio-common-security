
package com.codvio.security.service;

import com.codvio.security.dto.vo.AuthenticatedUserInfo;



public interface AuthenticationService {



    //~ --- [METHODS] --------------------------------------------------------------------------------------------------

    void cleanExpiredSessions(int timeout);



    //~ ----------------------------------------------------------------------------------------------------------------

    String createSession(AuthenticatedUserInfo userInfo);



    //~ ----------------------------------------------------------------------------------------------------------------

    AuthenticatedUserInfo findByToken(String token);



    //~ ----------------------------------------------------------------------------------------------------------------

    void updateSession(String token, AuthenticatedUserInfo userInfo);
}
