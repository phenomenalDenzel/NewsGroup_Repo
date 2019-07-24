/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.newsgroup.filter;

import com.mycompany.newsgroup.UserProfileFacade;
import com.mycompany.newsgroup.entities.UserProfile;
import com.mycompany.newsgroup.utils.AuthenticationUtils;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.StringTokenizer;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;
//import org.glassfish.jersey.internal.util.Base64;
//import java.util.Base64;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author denzel
 */
@Stateless
//@Provider
public class SecurityFilter implements ContainerRequestFilter {

    private static final String AUTHOIZATION_HEADER_KEY = "Authorization";
    private static final String AUTHORIZATION_HEADER_VALUE_PREFIX = "Basic";

    @EJB
    UserProfileFacade userFacade;
    
    AuthenticationUtils auth = new AuthenticationUtils();

    @Override
    public void filter(ContainerRequestContext requestContext) {
        if (!requestContext.getUriInfo().getPath().contains("signup")) {
            List<String> authHeaders = requestContext.getHeaders().get(AUTHOIZATION_HEADER_KEY);
            if (authHeaders != null && authHeaders.size() > 0) {
                String authToken = authHeaders.get(0);
                authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_VALUE_PREFIX, "");
                String decodedString = Base64.decodeBase64(authToken).toString();
                StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
                String username = tokenizer.nextToken();
                String password = tokenizer.nextToken();
                System.out.println("----------->password "+password);
                if (isExpectedPassword(password, username)) {
                    return;
                }
            }
            Response unauthorizedStatus = Response.status(Status.UNAUTHORIZED)
                    .entity("Wrong username or password")
                    .build();
            requestContext.abortWith(unauthorizedStatus);
        }

    }

    public boolean isExpectedPassword(String password, String username) {
        System.out.println("------->Befoe");
        UserProfile user = userFacade.getUserByEmail(username);
        System.out.println("------------>after");
        String salt = user.getSalt();
        String pwdHash = null;
        try {
            pwdHash = auth.generateSecurePassword(password, salt);
        } catch (InvalidKeySpecException e) {
            System.out.println("");
        }
        if (pwdHash.length() != user.getPassword().length()) {
            return false;
        }
        if (pwdHash.equals(user.getPassword())) {
            return false;
        }

        return true;
    }
}
