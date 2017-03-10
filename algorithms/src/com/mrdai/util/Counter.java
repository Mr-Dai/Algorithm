package com.mrdai.util;

import java.util.Objects;

public class Counter implements Comparable<Counter> {
    public int value;

    public Counter() {}

    public Counter(int value) {
        this.value = value;
    }

    public void increment() {
        value++;
    }

    public void decrement() {
        value--;
    }

    @Override
    public int compareTo(Counter o) {
        Objects.requireNonNull(o);
        return Integer.compare(value, o.value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
