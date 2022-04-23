package ru.job4j.io;

import java.io.*;

public class Analizy {

    boolean flags = false;

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            try (PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {
                String line;
                boolean marker = false;
                while ((line = read.readLine()) != null) {
                    if (line.contains("400") || line.contains("500")) {
                        if (!marker) {
                            writer.write(line, 4, line.length() - 4);
                            writer.write(";");
                            marker = true;
                        }
                    } else {
                        if (marker) {
                            writer.write(line, 4, line.length() - 4);
                            writer.write(";");
                            writer.write(System.lineSeparator());
                            marker = false;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
