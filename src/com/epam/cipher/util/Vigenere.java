package com.epam.cipher.util;

import com.epam.cipher.UnsupportedKeyException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.epam.cipher.util.FileWork.readFile;
import static com.epam.cipher.util.FileWork.writeFile;

public class Vigenere {
    public static final int LETTERS_TOTAL = 26;

    public static String code(String OriginalText) {
        String text = OriginalText.toLowerCase().replaceAll("\\s+", "");
        String codedText = "";
        String key = returnKeyFromConsole();
        writeFile(key, "key.txt");
        String repeatedKey = repeatKeyTilTextLength(key, text);
        for (int i = 0; i < text.length(); i++) {
            codedText += (char) (((text.charAt(i) - 'a' + repeatedKey.charAt(i) - 'a') % LETTERS_TOTAL) + 'a');
        }

        return codedText;
    }

    public static String decode(String codedText) {
        String key = readFile("key.txt");
        String repeatedKey = repeatKeyTilTextLength(key, codedText);
        String decodedText = "";
        for (int i = 0; i < codedText.length(); i++) {
            decodedText += (char) ((((codedText.charAt(i) - repeatedKey.charAt(i) + LETTERS_TOTAL)) % LETTERS_TOTAL) + 'a');
        }
        return decodedText;
    }

    private static String returnKeyFromConsole() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String key;
        System.out.println("Enter key:");
        try {
            key = br.readLine().toLowerCase().replaceAll("\\s+", "");
            if (checkKey(key)) {
                return key;
            } else {
                throw new UnsupportedKeyException("Invalid key");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String repeatKeyTilTextLength(String key, String text) {
        String repeatedKey = "";
        for (int i = 0; i < text.length(); i++) {
            repeatedKey += key.charAt(i % key.length());
        }
        return repeatedKey;
    }

    private static boolean checkKey(String key) {
        for (int i = 0; i < key.length(); ) {
            if (key.charAt(i) >= 'a' && key.charAt(i) <= 'z') {
                i++;
            } else {
                return false;
            }
        }
        return true;
    }
}
