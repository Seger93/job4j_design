package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (values.containsKey(key)) {
            return values.get(key);
        }
            throw new IllegalArgumentException("Такого ключа не существует");
    }

    private void parse(String[] args) {
        Arrays.stream(args)
                .forEach(s -> {
                    String[] ss = s.split("=", 2);
                    if (chekKey(ss)) {
                        values.put(ss[0].replaceFirst("-", ""), ss[1].replaceFirst("-", ""));
                    }
                });

    }

    private boolean chekKey(String[] s) {
        if (s[0].isEmpty() || s[1].isEmpty()) {
            throw new IllegalArgumentException("Некорректный ввод данных");
        }
        return true;
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
