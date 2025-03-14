package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T, R> {
    private final T first;
    private final R second;


    private Pair(T first, R last) {
        this.first = first;
        this.second = last;
    }

    public static <T, R> Pair<T, R> of(T first, R second) {
        return new Pair<>(first, second);
    }

    public T getFirst() {
        return first;
    }

    public R getSecond() {
        return second;
    }

    public void ifPresent(BiConsumer<? super T, ? super R> action) {
        if (first != null && second != null) {
            action.accept(first, second);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Pair<?, ?> pair = (Pair<?, ?>) obj;

        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
