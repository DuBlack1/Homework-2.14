package org.example.Service;


import org.example.Exception.*;

import java.util.Arrays;

public class StringListServiceImpl implements StringListService {

    private final String[] items;
    private int size;

    public StringListServiceImpl() {
        items = new String[10];
    }

    public StringListServiceImpl(int initSize) {
        items = new String[initSize];
    }

    private void validateItem(String item){
        if (item == null){
            throw new NotValeyException("Переданное значение пустое");
        }
    }

    private void validateSize(){
        if (size == items.length){
            throw new ThereIsNoSpaceInTheArrayException("Массив заполнен");
        }
    }

    private void validateIndex(int index){
        if (size == items.length){
            throw new IncorrectIndex("Не верно указан индекс");
        }
    }


    public String add(String item) {
        validateSize();
        validateItem(item);
        items[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        validateSize();
        validateItem(item);
        validateIndex(index);
        if (index == size){
            items[size++] = item;
            return item;
        }
        System.arraycopy(items, index, items, index+1, size-index);
        items[index]=item;
        return item;
    }

    @Override
    public String set(int index, String item) {
        validateIndex(index);
        validateItem(item);
        items[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        validateItem(item);
        int index = indexOf(item);
        return remove(index);
    }

    @Override
    public String remove(int index) {
        validateIndex(index);
        String item = items[index];
        if (index == size){
            items[size--] = null;
            return item;
        }
        System.arraycopy(items, index+1, items, index, size-index);
        size--;
        return item;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item)!= -1;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(item)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size-1; i >= 0; i--) {
            if (items[i].equals(item)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        validateIndex(index);
        return items[index];
    }

    @Override
    public boolean equals(String[] otherList) {
        return Arrays.equals(items, otherList);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        boolean value = true;
        for (int i = 0; i < items.length; i++)
            if (items[i] != null) {
                value = false;
                break;
            }
        return value;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(items, size);
    }
}
