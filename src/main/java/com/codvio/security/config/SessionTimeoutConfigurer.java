
package com.codvio.security.config;

import com.codvio.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;



public class SessionTimeoutConfigurer {



    //~ --- [INSTANCE FIELDS] ------------------------------------------------------------------------------------------

    @Autowired
    protected AuthenticationService authenticationService;



    //~ --- [METHODS] --------------------------------------------------------------------------------------------------

    @Scheduled(fixedRate = 60_000)
    public void reportCurrentTime() {

        cleanExpiredSessions();
    }



    //~ ----------------------------------------------------------------------------------------------------------------

    protected void cleanExpiredSessions() {

        authenticationService.cleanExpiredSessions(1_800_00);
    }
}
