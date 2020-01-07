/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.interceptor;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author MinhKudo
 */
//public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
//
//    public JWTLoginFilter(String url, AuthenticationManager authManager) {
//        super(new AntPathRequestMatcher(url));
//        setAuthenticationManager(authManager);
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        System.out.printf("JWTLoginFilter.attemptAuthentication: username/password= %s,%s", username, password);
//        System.out.println();
//
//        return getAuthenticationManager()
//                .authenticate(new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList()));
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
//            Authentication authResult) throws IOException, ServletException {
//
//        System.out.println("JWTLoginFilter.successfulAuthentication:");
//
//        // Write Authorization to Headers of Response.
//        TokenAuthenticationService.addAuthentication(response, authResult.getName());
//
//        String authorizationString = response.getHeader("Authorization");
//
//        System.out.println("Authorization String=" + authorizationString);
//    }
//}
