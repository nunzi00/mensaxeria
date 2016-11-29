/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.concello.mensaxeria;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static jdk.nashorn.internal.objects.NativeString.trim;

/**
 *
 * @author lnunzi
 */
public class Validar {
    public boolean isName(String cadena) {
        String expression = "^[a-zA-Z][ ]*$"; 
        CharSequence inputStr = trim(cadena);
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        return (boolean) matcher.matches();
    }

    public boolean isEmail() {
        return (boolean) true;
    }

    public boolean isPhone() {
        return (boolean) true;
    }

    public boolean isMobile() {
        return (boolean) true;
    }
}
