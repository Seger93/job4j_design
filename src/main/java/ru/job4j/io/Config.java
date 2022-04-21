package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().filter(Objects::nonNull)
                    .filter(s -> !s.startsWith("#"))
                    .filter(l -> !l.isEmpty())
                    .filter(this::chekKey)
                    .forEach(i -> {
                        String[] arr = i.split("=", 2);
                        if (arr[0].isEmpty() || arr[1].isEmpty()) {
                            throw new IllegalArgumentException("Некорректный ввод данных");
                        }
                        values.put(arr[0], arr[1]);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean chekKey(String s) {
        if (s.contains("=")) {
            return true;
        }
       throw new IllegalArgumentException("Строка не содержит =");
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}