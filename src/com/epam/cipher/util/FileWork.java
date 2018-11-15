package com.epam.cipher.util;

import java.io.*;

public class FileWork {

    public static String readFile(String fileToRead){
    fileNotFoundCreate(fileToRead);
    StringBuilder stringBuilder = new StringBuilder();

    try(BufferedReader in = new BufferedReader(new FileReader(fileToRead))) {
        try {
            String s;
            while ((s = in.readLine()) != null) {
                stringBuilder.append(s);
            }
            String textFromFile = new String(stringBuilder.toString());
            return textFromFile;
        } finally {
            in.close();
        }
    } catch(
    IOException e)

    {
        throw new RuntimeException(e);
    }

}

    public static void writeFile(String stringToWrite, String fileName) {

        fileNotFoundCreate(fileName);

        try (PrintWriter out = new PrintWriter(fileName)) {
            out.print(stringToWrite);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void fileNotFoundCreate(String fileName) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
                if (fileName == "input.txt") {
                    String str = "Default text";
                    try (PrintWriter in = new PrintWriter("input.txt")) {
                        in.print(str);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
