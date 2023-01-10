package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube")
                .isNotNull();
    }

    @Test
    void whatVertices() {
        Box box = new Box(4, 10);
        assertThat(4).
                isEqualTo(box.getNumberOfVertices())
                .isPositive()
                .isNotZero();
    }

    @Test
    void whatVerticesIsZero() {
        Box box = new Box(0, 10);
        assertThat(0).
                isEqualTo(box.getNumberOfVertices())
                .isZero();
    }

    @Test
    void whenExist() {
        Box box = new Box(0, 10);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void whenExistFalse() {
        Box box = new Box(-1, 10);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void areaIs() {
        Box box = new Box(0, 4);
        double res = box.getArea();
        assertThat(res).isNotZero()
                .isPositive();
    }

    @Test
    void areaIsZero() {
        Box box = new Box(0, 0);
        double res = box.getArea();
        assertThat(res).isZero();
    }
}