package com.praksa.ucenik.jatolog.util;

import android.content.Context;
import android.os.Build;

import java.util.Locale;

/**
 * Created by ucenik on 5/4/2018.
 */

public class Util {

    private static class Slovo {

        private static final String[] latinica = new String[]{"nj","lj","dž","e","r","t","z","u","i","o","p","š","đ","a","s","d","f","g","h","j","k","l","č","ć","ž","c","v","b","n","m",
                "Nj","Lj","Dž","E","R","T","Z","U","I","O","P","Š","Đ","A","S","D","F","G","H","J","K","L","Č","Ć","Ž","C","V","B","N","M"};
        private static final String[] cirilica = new String[]{"њ", "љ","џ","е","р","т","з","у","и","о","п","ш","ђ","а","с","д","ф","г","х","ј","к","л","ч","ћ","ж","ц","в","б","н","м",
                "Њ", "Љ","Џ","Е","Р","Т","З","У","И","О","П","Ш","Ђ","А","С","Д","Ф","Г","Х","Ј","К","Л","Ч","Ћ","Ж","Ц","В","Б","Н","М"};


        public static String getCirilicnoSlovo(String latinicnoSlovo) {
            for (int i = 0; i < latinica.length; i++) {
                if (latinicnoSlovo.equals(latinica[i])) {
                    return cirilica[i];
                }
            }
            return null;
        }

        public static String getLatinicnoSlovo(String cirilicnoSlovo) {
            for (int i = 0; i < cirilica.length; i++) {
                if (cirilicnoSlovo.equals(cirilica[i])) {
                    return latinica[i];
                }
            }
            return null;
        }

    }

    public static String prevediNaCirilicu(String tekst) {
        return izmijeniSlova(tekst, 1);
    }

    public static String prevediNaLatinicu(String tekst) {
        return izmijeniSlova(tekst, 2);
    }

    private static String izmijeniSlova(String tekst, int flag) {

        StringBuilder stringBuilder = new StringBuilder(tekst.length());

        //prevod sa latinice na cirilicu
        // nj -> њ; lj -> љ; dž -> џ;
        // Nj -> Њ; Lj -> Љ; Dž -> Џ;
        if (flag == 1) {
            for (int i = 0; i < tekst.length(); i++) {
                String slovo = tekst.charAt(i) + "";
                if ( ("N").equals(slovo) || ("n").equals(slovo) || ("L").equals(slovo) || ("l").equals(slovo) ) {
                    if (("j").equals("" + tekst.charAt(i+1)) || ("J").equals("" + tekst.charAt(i+1))) {
                        slovo += tekst.charAt(i+1);
                        i++;
                    }
                }
                else if  (("D").equals(slovo) || ("d").equals(slovo)) {
                    if (("ž").equals("" + tekst.charAt(i+1)) || ("Ž").equals("" + tekst.charAt(i+1))) {
                        slovo += tekst.charAt(i+1);
                        i++;
                    }
                }
                String slovoPrevod = Slovo.getCirilicnoSlovo(slovo);
                if (slovoPrevod == null) {
                    stringBuilder.append(tekst.charAt(i));
                }
                else{
                stringBuilder.append(slovoPrevod);
                }
            }
        }
        //prevod sa cirilice na latinicu
        else if (flag == 2) {
            for (int i = 0; i < tekst.length(); i++) {
                String slovoPrevod = Slovo.getLatinicnoSlovo("" + tekst.charAt(i));
                if (slovoPrevod == null) {
                    stringBuilder.append(tekst.charAt(i));
                }
                else{
                    stringBuilder.append(slovoPrevod);
                }
            }
        }
        else {
            return null;
        }

        return stringBuilder.toString();

    }

    public static Locale getCurrentLocale(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return context.getResources().getConfiguration().getLocales().get(0);
        } else{
            //noinspection deprecation
            return context.getResources().getConfiguration().locale;
        }
    }

}
