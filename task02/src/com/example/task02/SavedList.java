package com.example.task02;

import java.io.*;
import java.nio.file.Files;
import java.util.AbstractList;
import java.util.ArrayList;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private final ArrayList<E> list = new ArrayList<>();
    private final File file;

    public SavedList(File file) {
        this.file = file;
        loadFromFile();
    }

    private void loadFromFile() {
        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream stream = new ObjectInputStream(Files.newInputStream(file.toPath()))) {
            @SuppressWarnings("unchecked")
            ArrayList<E> loadedList = (ArrayList<E>) stream.readObject();
            list.addAll(loadedList);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error when loading data from file", e);
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream stream = new ObjectOutputStream(Files.newOutputStream(file.toPath()))) {
            stream.writeObject(list);
        } catch (IOException e) {
            throw new RuntimeException("Error when saving data to file", e);
        }
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E oldElement = list.set(index, element);
        saveToFile();
        return oldElement;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        saveToFile();
    }

    @Override
    public E remove(int index) {
        E removedElement = list.remove(index);
        saveToFile();
        return removedElement;
    }
}
