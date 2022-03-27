package ru.job4j.collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.NoSuchElementException;

public class SimpleQueueTest {

    @Test
    public void whenPushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.pushIn(1);
        int rsl = queue.poll();
        assertThat(rsl, is(1));
    }

    @Test
    public void when2PushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.pushIn(1);
        queue.pushIn(2);
        int rsl = queue.poll();
        assertThat(rsl, is(1));
    }

    @Test
    public void when2PushPollPushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.pushIn(1);
        queue.poll();
        queue.pushIn(2);
        int rsl = queue.poll();
        assertThat(rsl, is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.poll();
    }

    @Test
    public void whenPushPushPollAndPush() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.pushIn(1);
        queue.pushIn(2);
        queue.poll();
        queue.pushIn(3);
        assertThat(queue.poll(), is(2));
    }
}