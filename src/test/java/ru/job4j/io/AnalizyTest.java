package ru.job4j.io;

import org.junit.Test;

public class AnalizyTest {

    @Test
    public void timeToLog() {
        String sourse = ".\\analizy\\sourse.csv";
        String target = ".\\analizy\\target.csv";
        Analizy analizy = new Analizy();
        analizy.unavailable(sourse, target);
    }
}