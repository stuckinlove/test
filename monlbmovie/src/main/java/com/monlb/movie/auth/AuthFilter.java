package com.monlb.movie.auth;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

public class AuthFilter extends AbstractPreAuthenticatedProcessingFilter {

    private final String headerName;

    public AuthFilter(String headerName) {
        this.headerName = headerName;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader(headerName);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest httpServletRequest) {
        return null; // No creds when using API key
    }
}
