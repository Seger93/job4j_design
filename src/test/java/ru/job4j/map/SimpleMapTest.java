package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.NoSuchElementException;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleMapTest {

    @Test
    public void putSameBucket() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        boolean rsl = map.put(0, "Hello");
        boolean rsl2 = map.put(0, "World");
        assertTrue(rsl);
        assertFalse(rsl2);
    }

    @Test
    public void putDiffBucket() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        boolean rsl = map.put(0, "Hello,");
        boolean rsl2 = map.put(1, "World!");
        boolean rsl3 = map.put(2, "What's");
        boolean rsl4 = map.put(3, "Up?");
        assertTrue(rsl);
        assertTrue(rsl2);
        assertTrue(rsl3);
        assertTrue(rsl4);
    }

    @Test
    public void putAndExpand() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        boolean rsl = map.put(0, "0");
        boolean rsl2 = map.put(1, "1");
        boolean rsl3 = map.put(2, "2");
        boolean rsl4 = map.put(3, "3");
        boolean rsl5 = map.put(4, "4");
        boolean rsl6 = map.put(5, "5");
        boolean rsl7 = map.put(6, "6");
        boolean rsl8 = map.put(7, "7");
        boolean rsl9 = map.put(8, "8");
        boolean rsl10 = map.put(9, "9");
        boolean rsl11 = map.put(10, "9");
        boolean rsl12 = map.put(11, "10");
        boolean rsl13 = map.put(12, "10");
        boolean rsl14 = map.put(13, "10");
        boolean rsl15 = map.put(14, "10");
        boolean rsl16 = map.put(15, "10");
        boolean rsl17 = map.put(16, "10");
        assertTrue(rsl17);
    }

    @Test
    public void put7AndExpand() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        boolean rsl = map.put(0, "0w");
        assertTrue(rsl);
        String s0 = map.get(0);
        assertSame("0w", s0);
        boolean rsl2 = map.put(1, "1");
        boolean rsl3 = map.put(2, "2");
        boolean rsl4 = map.put(3, "3");
        boolean rsl5 = map.put(4, "4");
        boolean rsl6 = map.put(5, "5");
        boolean rsl7 = map.put(6, "6");
        String s1 = map.get(1);
        assertSame("1", s1);
    }

    @Test
    public void get123AndPutInTheSameBucket() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        boolean rsl = map.put(0, "123");
        boolean rsl1 = map.put(1, "123");
        boolean rsl2 = map.put(1, "1");
        String expected = "123";
        assertThat(map.get(0), is(expected));
        assertThat(map.get(1), is(expected));
    }

    @Test
    public void getNull() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(0, "123");
        String expected = "123";
        assertThat(map.get(0), is(expected));
        assertNull(map.get(1));
    }

    @Test
    public void removeAll() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(0, "First");
        map.put(1, "Second");
        map.put(2, "Third");
        assertTrue(map.remove(0));
        assertTrue(map.remove(1));
        assertTrue(map.remove(2));
    }

    @Test
    public void removeFalse() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertFalse(map.remove(0));
        assertFalse(map.remove(3));
        assertFalse(map.remove(123));
    }

    @Test(expected = NoSuchElementException.class)
    public void noElementIterator() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        Iterator<Integer> iter = map.iterator();
        iter.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void concurrentModIterator() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(0, "First");
        Iterator<Integer> iter = map.iterator();
        map.put(1, "Second");
        iter.next();
    }

    @Test
    public void iteratorHasNextThanNull() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(0, "First");
        map.put(1, "Second");
        map.put(3, "Third");
        Iterator<Integer> iter = map.iterator();
        assertTrue(iter.hasNext());
        assertThat(iter.next(), is(0));
        assertTrue(iter.hasNext());
        assertThat(iter.next(), is(1));
        assertTrue(iter.hasNext());
        assertThat(iter.next(), is(3));
        assertFalse(iter.hasNext());
    }
}