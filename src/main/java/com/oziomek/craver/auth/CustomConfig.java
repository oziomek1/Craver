package com.oziomek.craver.auth;

import org.glassfish.jersey.server.ResourceConfig;

public class CustomConfig extends ResourceConfig {

    public CustomConfig() {
        packages("com.oziomek.craver");
        register(AuthenticationFilter.class);

    }
}
