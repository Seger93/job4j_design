package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    private String botSays(List<String> data) {
        return data.get((int) (Math.random() * data.size()));
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        List<String> logOut = new ArrayList<>();
        String question = "";
        List<String> answer = readPhrases();
        while (!OUT.equals(question)) {
            question = scanner.nextLine();
            logOut.add(question);
            if (STOP.equals(question)) {
                while (!CONTINUE.equals(question)) {
                    question = scanner.nextLine();
                    logOut.add(question);
                }
            }
            String botAnswer = botSays(answer);
            logOut.add(botAnswer);
            System.out.println(botAnswer);
        }
        saveLog(logOut);
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path, Charset.defaultCharset()))) {
            br.lines().forEach(rsl::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(botAnswers, Charset.defaultCharset(), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validate(String[] s) {
        File file = new File(s[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException("Файл не существует");
        }
        if (!s[1].endsWith(".txt")
                && !s[0].endsWith(".txt")) {
            throw new IllegalArgumentException("Некорректный формат файла");
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(args[0], args[1]);
        cc.validate(args);
        cc.run();
    }
}

