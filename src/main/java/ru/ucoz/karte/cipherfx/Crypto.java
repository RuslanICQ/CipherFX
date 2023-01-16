package ru.ucoz.karte.cipherfx;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Crypto {

    static String CipherMode1(String text, String simple_key) {
        String key = HashCode(simple_key, "SHA-512");
        char text_ch;
        char key_ch;
        int ciph_int;
        String ciph_symb = "";
        String ciph_str = "";
        int x, y = 0;
        for (x = 0; x < text.length(); x = x + 1) {
            text_ch = text.charAt(x);
            key_ch = key.charAt(y);
            ciph_int = text_ch + key_ch;

            if (y == key.length() - 1) y = 0;
            else y = y + 1;

            ciph_symb = String.valueOf(ciph_int);
            ciph_str = ciph_str + (char) Integer.parseInt(ciph_symb);
        }
        return ciph_str.strip();
    }

    static String DecipherMode1(String ciph_text, String simple_key) {
        String key = HashCode(simple_key, "SHA-512");
        char key_ch;
        int ciph_int;
        String ciph_symb = "";
        String text = "";
        int x, y = 0;
        try {
            for (x = 0; x < ciph_text.length(); x = x + 1) {
                ciph_symb = ciph_text.substring(x, x + 1);
                ciph_int = ciph_symb.charAt(0);
                key_ch = key.charAt(y);
                if (y == key.length() - 1) y = 0;
                else y = y + 1;
                text = text + Character.toString(ciph_int - key_ch);
            }
            return text;
        } catch (Exception e) {
            return "ERROR: "+e.toString();
        }

    }

    static String CipherMode2(String text, String simple_key){
        String key = HashCode(simple_key, "SHA-512");
        int block_size = 4;
        char text_ch;
        char key_ch;
        int ciph_int;
        String ciph_symb = "";
        String ciph_str = "";
        int x, y = 0;
        for(x = 0; x < text.length(); x = x + 1) {
            text_ch = text.charAt(x);
            key_ch = key.charAt(y);
            ciph_int = text_ch + key_ch;

            if (y == key.length()-1) y = 0;
            else y = y + 1;

            ciph_symb = String.valueOf(ciph_int);
            while(ciph_symb.length()%block_size != 0){
                ciph_symb ="0" + ciph_symb;
            }
            ciph_str = ciph_str + ciph_symb;
        }
        return ciph_str.strip();
    }

    static String DecipherMode2(String ciph_text, String simple_key){
        String key = HashCode(simple_key, "SHA-512");
        int block_size = 4;
        char text_ch;
        char key_ch;
        int ciph_int;
        String ciph_symb = "";
        String text = "";
        int x, y = 0;
        for(x = 0; x < ciph_text.length(); x = x + block_size) {
            ciph_symb = ciph_text.substring(x, x + block_size);
            ciph_int = Integer.parseInt(ciph_symb);
            key_ch = key.charAt(y);
            if (y == key.length()-1) y = 0;
            else y = y + 1;
            text = text + Character.toString(ciph_int - key_ch);
        }
        return text;
    }

    static String CipherMode3(String text, String simple_key){
        String key = HashCode(simple_key, "SHA-512");
        int block_size = 4;
        char text_ch;
        char key_ch;
        int ciph_int;
        String ciph_symb = "";
        String ciph_str = "";
        int x, y = 0;
        for(x = 0; x < text.length(); x = x + 1) {
            text_ch = text.charAt(x);
            key_ch = key.charAt(y);
            ciph_int = text_ch + key_ch;
            String ciph_hex = Integer.toHexString(ciph_int);

            if (y == key.length()-1) y = 0;
            else y = y + 1;

            ciph_symb = String.valueOf(ciph_hex);
            while(ciph_symb.length()%block_size != 0){
                ciph_symb ="0" + ciph_symb;
            }
            ciph_str = ciph_str + ciph_symb;
        }
        return ciph_str.strip();
    }

    static String DecipherMode3(String ciph_text, String simple_key){
        String key = HashCode(simple_key, "SHA-512");
        int block_size = 4;
        char text_ch;
        char key_ch;
        int ciph_int;
        String ciph_symb = "";
        String text = "";
        int x, y = 0;
        for(x = 0; x < ciph_text.length(); x = x + block_size) {
            ciph_symb = ciph_text.substring(x, x + block_size);
            ciph_int = Integer.parseInt(ciph_symb, 16);
            key_ch = key.charAt(y);
            if (y == key.length()-1) y = 0;
            else y = y + 1;
            text = text + Character.toString(ciph_int - key_ch);
        }
        return text;
    }

    //----------------------------------------------------------------------------------------------------------------

    static String HashCode(String input, String hash_algo) {
        String output = "";
        try {
            MessageDigest md = MessageDigest.getInstance(hash_algo);
            md.update(input.getBytes());
            byte[] digest = md.digest();
            output = new BigInteger(1, digest).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

}
