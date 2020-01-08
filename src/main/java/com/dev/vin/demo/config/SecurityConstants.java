/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.config;

/**
 *
 * @author MinhKudo
 */
public class SecurityConstants {

    public static final long EXPIRATION = 864_000_000; // 10 ngày
    public static final String SECRET = "supersecret";
    public static final String HEADER = "Authorization";
    public static final String PREFIX = "Bearer ";
}
