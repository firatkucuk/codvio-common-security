
package io.codv.springwind.service;

import io.codv.springwind.dto.vo.AuthenticatedUserInfo;



public interface AuthenticationService {



    //~ --- [METHODS] --------------------------------------------------------------------------------------------------

    void cleanExpiredSessions();



    //~ ----------------------------------------------------------------------------------------------------------------

    AuthenticatedUserInfo findByToken(String token);
}
