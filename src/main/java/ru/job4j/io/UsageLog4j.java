package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Sergei";
        int age = 29;
        double growth = 184.4;
        float taskPerDay = 1.45F;
        char lit = 'G';
        boolean write = true;
        byte system = 64;
        long days = 217;
        short works = 5;
        LOG.debug("User info name : {}, age : {}, growth : {}, taskPerDay : {},"
                        + " lit : {}, write : {}, system : {}, days :{}, works :{} ",
                name, age, growth, taskPerDay, lit, write, system, days, works);
    }
}