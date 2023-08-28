package org.internetbanking.business.utils;

public class inputCharacters {
    public static boolean isNumber(String value) {
        int i;
        for(i = 0; i < value.length(); i++) {
            char a = value.charAt(i);
            if(a >= '0' && a <= '9') {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean isSpecialCharacters(String value) {
        int i;
        for(i = 0; i < value.length(); i++) {
            char a = value.charAt(i);
            if(!(a >= '0' && a <= '9') && !(a >= 'A' && a <= 'Z') && !(a >= 'a' && a <= 'z')) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLetters(String value){
        int i;
        for(i = 0; i < value.length(); i++) {
            char a = value.charAt(i);
            if((a >= 'A' && a <= 'Z') || (a >= 'a' && a <= 'z')) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

}
