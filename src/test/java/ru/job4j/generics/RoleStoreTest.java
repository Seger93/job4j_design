package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRoleIsExampleRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "ExampleRole"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("ExampleRole"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "ExampleRole"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRoleIsExampleRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "ExampleRole"));
        store.add(new Role("1", "ExampleRoleSecond"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("ExampleRole"));
    }

    @Test
    public void whenReplaceThenRoleIsExampleRoleSecond() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "ExampleRole"));
        store.replace("1", new Role("1", "ExampleRoleSecond"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("ExampleRoleSecond"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "ExampleRole"));
        store.replace("10", new Role("10", "ExampleRoleSecond"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("ExampleRole"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "ExampleRole"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRoleIsExampleRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "ExampleRole"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRole(), is("ExampleRole"));
    }
}