/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.util;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author MinhKudo
 */
public class Share {

    public static String token_student;
    public static String token_teach;
    public static String token_admin;
    public static ArrayList<String> listSessionStudent = new ArrayList<>();
    public static ArrayList<String> listSessionTeach = new ArrayList<>();
    public static ArrayList<String> listSessionAdmin = new ArrayList<>();

    public static boolean checkSessionStudent(String session) {
        for (String string : listSessionStudent) {
            System.out.println("String: " + string);
            if (string.equals(session)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkSessionTeach(String session) {
        for (String string : listSessionTeach) {
            if (string.equals(session)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkSessionAdmin(String session) {
        for (String string : listSessionAdmin) {
            if (string.equals(session)) {
                return true;
            }
        }
        return false;
    }
}
