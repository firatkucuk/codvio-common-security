
package io.codv.springwind.filter;


import io.codv.springwind.security.StatelessAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 * This filter looks up the request headers for "Authentication" header and matches the fetched token via using given
 * authentication method.
 */
public class RestAuthenticationFilter extends GenericFilterBean {



    //~ --- [STATIC FIELDS/INITIALIZERS] -------------------------------------------------------------------------------

    private static final Logger LOG = LoggerFactory.getLogger(RestAuthenticationFilter.class);



    //~ --- [INSTANCE FIELDS] ------------------------------------------------------------------------------------------

    private final AuthenticationManager authenticationManager;



    //~ --- [CONSTRUCTORS] ---------------------------------------------------------------------------------------------

    public RestAuthenticationFilter(final AuthenticationManager authenticationManager) {

        this.authenticationManager = authenticationManager;
    }



    //~ --- [METHODS] --------------------------------------------------------------------------------------------------

    @Override
    public void doFilter(
            final ServletRequest  servletRequest,
            final ServletResponse servletResponse,
            final FilterChain     filterChain) throws IOException, ServletException {

        final HttpServletRequest  request    = (HttpServletRequest) servletRequest;
        final HttpServletResponse response   = (HttpServletResponse) servletResponse;
        final String              authHeader = request.getHeader("Authorization");

        if (authHeader != null) {

            final String         token               = authHeader.replace("Bearer ", "");
            final Authentication authenticationToken = new StatelessAuthenticationToken(token);

            LOG.trace("Authorization token fetched!");

            final Authentication authResult = authenticationManager.authenticate(authenticationToken);

            if (authResult != null) {
                SecurityContextHolder.getContext().setAuthentication(authResult);

                LOG.trace("Authentication result added to security context holder.");
            } else {
                LOG.trace("No authentication result!");
            }
        } else {
            LOG.trace("No Authorization header found.");
        }

        LOG.debug("REST Authentication Filter applied.");

        filterChain.doFilter(request, response);
    }
}
