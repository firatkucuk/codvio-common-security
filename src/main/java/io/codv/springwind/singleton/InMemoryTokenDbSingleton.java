
package io.codv.springwind.singleton;


import io.codv.springwind.dto.vo.AuthenticatedUserInfo;
import java.util.*;



public class InMemoryTokenDbSingleton {



    //~ --- [STATIC FIELDS/INITIALIZERS] -------------------------------------------------------------------------------

    public static final int                 SESSION_TIMEOUT = 900_000; // 15 Minutes
    private static InMemoryTokenDbSingleton instance        = new InMemoryTokenDbSingleton();



    //~ --- [INSTANCE FIELDS] ------------------------------------------------------------------------------------------

    private Map<String, SessionInfo> tokenDb;



    //~ --- [CONSTRUCTORS] ---------------------------------------------------------------------------------------------

    private InMemoryTokenDbSingleton() {

        this.tokenDb = new HashMap<>();
    }



    //~ --- [METHODS] --------------------------------------------------------------------------------------------------

    public static InMemoryTokenDbSingleton getInstance() {

        return instance;
    }



    //~ --- [METHODS] --------------------------------------------------------------------------------------------------

    public String createNewToken(final AuthenticatedUserInfo userInfo) {

        final Calendar now      = Calendar.getInstance();
        final String   newToken = UUID.randomUUID().toString();

        final SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setLastActionTime(now.getTimeInMillis());
        sessionInfo.setUserInfo(userInfo);

        this.tokenDb.put(newToken, sessionInfo);

        return newToken;
    }



    //~ ----------------------------------------------------------------------------------------------------------------

    public void deleteExpiredTokens() {

        final Calendar                                 now      = Calendar.getInstance();
        final Iterator<Map.Entry<String, SessionInfo>> iterator = tokenDb.entrySet().iterator();

        while (iterator.hasNext()) {
            final Map.Entry<String, SessionInfo> entry        = iterator.next();
            final long                           creationTime = entry.getValue().lastActionTime;

            if (now.getTimeInMillis() - creationTime > SESSION_TIMEOUT) {
                iterator.remove();
            }
        }
    }



    //~ ----------------------------------------------------------------------------------------------------------------

    public AuthenticatedUserInfo findUser(final String token) {

        if (tokenDb.containsKey(token)) {
            final Calendar    now         = Calendar.getInstance();
            final SessionInfo sessionInfo = tokenDb.get(token);

            sessionInfo.setLastActionTime(now.getTimeInMillis());

            return sessionInfo.getUserInfo();
        }

        return null;
    }



    //~ ----------------------------------------------------------------------------------------------------------------

    public boolean hasToken(final String token) {

        if (tokenDb.containsKey(token)) {
            final Calendar    now         = Calendar.getInstance();
            final SessionInfo sessionInfo = tokenDb.get(token);

            sessionInfo.setLastActionTime(now.getTimeInMillis());

            return true;
        }

        return false;
    }



    //~ --- [INNER CLASSES] --------------------------------------------------------------------------------------------

    private class SessionInfo {



        //~ --- [INSTANCE FIELDS] --------------------------------------------------------------------------------------

        private long                  lastActionTime;
        private AuthenticatedUserInfo userInfo;



        //~ --- [METHODS] ----------------------------------------------------------------------------------------------

        public long getLastActionTime() {

            return lastActionTime;
        }



        //~ ------------------------------------------------------------------------------------------------------------

        public AuthenticatedUserInfo getUserInfo() {

            return userInfo;
        }



        //~ ------------------------------------------------------------------------------------------------------------

        public void setLastActionTime(final long lastActionTime) {

            this.lastActionTime = lastActionTime;
        }



        //~ ------------------------------------------------------------------------------------------------------------

        public void setUserInfo(final AuthenticatedUserInfo userInfo) {

            this.userInfo = userInfo;
        }

    }
}
