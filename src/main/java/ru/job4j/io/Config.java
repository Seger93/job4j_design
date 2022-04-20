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
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            in.lines().filter(Objects::nonNull)
                    .filter(s -> !s.startsWith("#"))
                    .filter(l -> l.length() > 0)
                    .filter(a -> {
                        boolean rsl = true;
                        if (a.startsWith("=") || a.endsWith("=")) {
                            throw new IllegalArgumentException();
                        }
                        return rsl;
                    })
                    .forEach(i -> {
                        String[] arr = i.split("=", 2);
                        if (!i.contains("=")) {
                            throw new IllegalArgumentException();
                        }
                        values.put(arr[0], arr[1]);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
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