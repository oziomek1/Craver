package com.oziomek.craver.auth;

import com.oziomek.craver.persistence.model.Profile;
import com.oziomek.craver.service.ProfileService;
import org.glassfish.jersey.internal.util.Base64;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter{

    @Context
    private ResourceInfo resourceInfo;

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic ";
    private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
            .entity("Cannot access this resource")
            .build();
    private static final Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN)
            .entity("Access blocked")
            .build();

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        Method method = resourceInfo.getResourceMethod();
        //Allowed for anyone
        if (!method.isAnnotationPresent(PermitAll.class)) {
            //Denied for anyone
            if (method.isAnnotationPresent(DenyAll.class)) {
                containerRequestContext.abortWith(ACCESS_FORBIDDEN);
                return;
            }

            //Get headers
            final MultivaluedMap<String, String> headers = containerRequestContext.getHeaders();

            //Auth header
            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

            //if no auth info present, block
            if (authorization == null || authorization.isEmpty()) {
                containerRequestContext.abortWith(ACCESS_DENIED);
                return;
            }

            //encode username/password
            final String encodedData = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME, "");

            String usernamePassword = new String(Base64.decode(encodedData.getBytes()));

            //split data
            final StringTokenizer tokenizer = new StringTokenizer(usernamePassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();

            if (method.isAnnotationPresent(RolesAllowed.class)) {
                RolesAllowed rolesAllowed = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<>(Arrays.asList(rolesAllowed.value()));

                if (!isUserAllowed(username, password, rolesSet)) {
                    containerRequestContext.abortWith(ACCESS_DENIED);
                    return;
                }
            }
        }
    }

    private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet) {
        boolean isAllowed = false;

        Profile profile = new ProfileService().getProfile(username);
        if (!profile.getPassword().equals(password)) {
            Response.status(Response.Status.UNAUTHORIZED).build();
            return isAllowed;
        }

        if (rolesSet.contains(profile.getRole())) {
            isAllowed = true;
        }
        return isAllowed;
    }
}
