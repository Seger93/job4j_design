package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    private void validation(File file, String delimiter, String out) {
        if (!file.exists()) {
            throw new IllegalArgumentException("ФАйл не существует");
        }
        if (!";".equals(delimiter)) {
            throw new IllegalArgumentException("Неверный разделитель");
        }
    }

    public static void handle(ArgsName argsName) throws Exception {
        CSVReader csvReader = new CSVReader();
        String file = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        File fileRead = new File(file);
        csvReader.validation(fileRead, delimiter, out);
        try (Scanner scanner = new Scanner(new BufferedReader(
                new FileReader(fileRead))).useDelimiter(System.lineSeparator())) {
            List<String> filterColumns = List.of(filter.split(","));
            StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
            String[] columns = scanner.nextLine().split(";");
            ArrayList<Integer> indexColumns = new ArrayList<>();
            StringJoiner headerJoiner = new StringJoiner(";");
            for (int i = 0; i < columns.length; i++) {
                if (filterColumns.contains(columns[i])) {
                    indexColumns.add(i);
                    headerJoiner.add(columns[i]);
                }
            }
            stringJoiner.merge(headerJoiner);
            String outputLine = null;
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(delimiter);
                StringJoiner lineJoiner = new StringJoiner(";");
                indexColumns.forEach(el -> lineJoiner.add(line[el]));
                stringJoiner.merge(lineJoiner);
                outputLine = stringJoiner + System.lineSeparator();

            }
            if ("stdout".equals(out)) {
                System.out.println(outputLine);
            } else {
                try (FileOutputStream outputStream = new FileOutputStream(out)) {
                    outputStream.write(outputLine.getBytes(StandardCharsets.UTF_8));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("array is empty.");
        }
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}