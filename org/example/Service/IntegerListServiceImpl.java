package org.example.Service;


import org.example.Exception.IncorrectIndex;
import org.example.Exception.NotValeyException;
import org.example.Exception.ThereIsNoSpaceInTheArrayException;

import java.util.Arrays;

public class IntegerListServiceImpl implements IntegerListService {

    private Integer[] items;
    int size = 0;

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

    public static void bubbleSort(IntegerListServiceImpl list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    swap(list, j, j + 1);
                }
            }
        }
    }

    public static void selectionSort(IntegerListServiceImpl list) {
        for (int i = 0; i < list.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j) < list.get(minIndex)) {
                    minIndex = j;
                }
            }
            swap(list, i, minIndex);
        }
    }

    public static void insertionSort(IntegerListServiceImpl list) {
        for (int i = 1; i < list.size(); i++) {
            int current = list.get(i);
            int j = i;
            while (j > 0 && list.get(j - 1) > current) {
                list.set(j, list.get(j - 1));
                j--;
            }
            list.set(j, current);
        }
    }

    private void privateInsertionSort() {
        for (int i = 1; i < items.length; i++) {
            if (items[i] != null) {
                int current = items[i];
                int j = i;
                while (j > 0 && items[j - 1] > current) {
                    items[j] = items[j - 1];
                    j--;
                }
                items[j] = current;
            }
        }
    }

    private boolean binarySearch(Integer x) {
        int min = 0;
        int max = this.size() - 1;
        while (min <= max) {
            int mid = (max + min) / 2;
            if (items[mid].equals(x)) {
                return true;
            }
            if (x < items[mid]) {
                max = mid - 1;
            } else {
                min = mid +1;
            }
        }
        return false;
    }

    private static void swap(IntegerListServiceImpl list, int index1, int index2) {
        int tmp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, tmp);
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
