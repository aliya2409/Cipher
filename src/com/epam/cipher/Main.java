package com.epam.cipher;

import static com.epam.cipher.util.FileWork.readFile;
import static com.epam.cipher.util.FileWork.writeFile;
import static com.epam.cipher.util.Vigenere.code;
import static com.epam.cipher.util.Vigenere.decode;

public class Main {
    public static void main(String[] args) {
        writeFile(code(readFile("input.txt")), "output.txt");
        writeFile(decode(readFile("output.txt")), "decodedOutput.txt");
    }
}