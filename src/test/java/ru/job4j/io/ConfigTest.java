package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Sergei"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairIllegalArgument() {
        String path = "./appTest1.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenTest2EmptyLine() {
        String path = "./appTest2.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("car"), is("opel"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairIllegalArguments() {
        String path = "./appTest3.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenTestDoubleEquals() {
        String path = "./appTest4.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("Piter"), is("Love=1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoEquals() {
        String path = "./appTest5.properties";
        Config config = new Config(path);
        config.load();
    }
}