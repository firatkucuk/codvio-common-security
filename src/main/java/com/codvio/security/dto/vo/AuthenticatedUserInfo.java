
package com.codvio.security.dto.vo;

import java.io.Serializable;



public interface AuthenticatedUserInfo extends Serializable {



    //~ --- [METHODS] --------------------------------------------------------------------------------------------------

    /**
     * Returns display name of the current authenticated user.
     *
     * @return  display name
     */
    String getDisplayName();



    //~ ----------------------------------------------------------------------------------------------------------------

    /**
     * Principal can be unique username, login ID, e-mail
     *
     * @return  unique principal
     */
    String getPrincipal();
}
