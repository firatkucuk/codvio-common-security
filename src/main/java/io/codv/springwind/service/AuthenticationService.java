
package io.codv.springwind.service;

import io.codv.springwind.dto.vo.AuthenticatedUserInfo;



public interface AuthenticationService {



    //~ --- [METHODS] --------------------------------------------------------------------------------------------------

    void cleanExpiredSessions();



    //~ ----------------------------------------------------------------------------------------------------------------

    String createSession(AuthenticatedUserInfo userInfo);



    //~ ----------------------------------------------------------------------------------------------------------------

    AuthenticatedUserInfo findByToken(String token);
}
