package main.java.storage;

import java.io.*;
import java.util.*;

public class FileManager {

    public static void writeLine(String filePath, String data) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));
            bw.write(data);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println("Error writing file: " + filePath);
        }
    }

    public static List<String> readAll(String filePath) {

        List<String> lines = new ArrayList<>();

        try {

            File file = new File(filePath);

            if (!file.exists())
                return lines;

            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;

            while ((line = br.readLine()) != null)
                lines.add(line);

            br.close();

        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
        }

        return lines;
    }

    public static void writeAll(String filePath, List<String> lines) {

        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false));

            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }

            bw.close();

        } catch (IOException e) {
            System.out.println("Error rewriting file: " + filePath);
        }
    }
}