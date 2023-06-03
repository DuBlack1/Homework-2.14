package org.example.Service;


import org.example.Exception.IncorrectIndex;
import org.example.Exception.NotValeyException;
import org.example.Exception.ThereIsNoSpaceInTheArrayException;

import java.util.Arrays;

public class IntegerListServiceImpl implements IntegerListService {

    private final Integer[] items;
    private int size;

    public IntegerListServiceImpl() {
        items = new Integer[10];
    }

    public IntegerListServiceImpl(int initSize) {
        items = new Integer[initSize];
    }

    private void validateItem(Integer item){
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


    public Integer add(Integer item) {
        validateSize();
        validateItem(item);
        items[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
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
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        items[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = indexOf(item);
        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer item = items[index];
        if (index == size){
            items[size--] = null;
            return item;
        }
        System.arraycopy(items, index+1, items, index, size-index);
        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] storageCopy = toArray();
        sort(storageCopy);
        return binarySearch(storageCopy, item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(item)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size-1; i >= 0; i--) {
            if (items[i].equals(item)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return items[index];
    }

    @Override
    public boolean equals(Integer[] otherList) {
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
    public Integer[] toArray() {
        return Arrays.copyOf(items, size);
    }

    private void sort(Integer[] arr){
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j>0 && arr[j-i] >= temp){
                arr[j] = arr[j-i];
                j--;
            }
            arr[j] = temp;
        }
    }

    private boolean binarySearch(Integer[] arr, int items) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (items == arr[mid]) {
                return true;
            }

            if (items < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
}