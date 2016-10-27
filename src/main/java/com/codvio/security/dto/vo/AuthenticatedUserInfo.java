
package com.codvio.security.dto.vo;

import java.io.Serializable;
import java.util.List;



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



    //~ ----------------------------------------------------------------------------------------------------------------

    /**
     * Returns role names of the authenticated users
     *
     * @return  role names
     */
    List<String> getRoles();
}
