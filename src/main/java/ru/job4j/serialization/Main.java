package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact(111, "11-111"),
                new String[]{"Worker", "Married"});
        final Person person1 = new Person(true, 29, new Contact(178, "911-11-111"),
                new String[] {"Married", "WorksToJAva", "AngryMan"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));
        final Gson gson1 = new GsonBuilder().create();
        System.out.println(gson1.toJson(person1));
        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";

        final Person personMod = gson.fromJson(personJson, Person.class);
        final Person personMod2 = gson1.fromJson(personJson, Person.class);
        System.out.println(personMod2);
        System.out.println(personMod);
    }
}