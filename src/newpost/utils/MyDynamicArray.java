package newpost.utils;

import java.util.Arrays;

/**
 * Created by Serhii Fursenko on 08.07.2016.
 * fyrsenko@gmail.com
 */
public class MyDynamicArray {

    private Object[] array;
    private int size;

    public MyDynamicArray() {
        this.array = new Object[10];
    }

    public void add(Object obj) {
        if (size == array.length) {
            growArraySize();
        }
        array[size++] = obj;
    }

    public int Size() {
        return size;
    }

    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    public Object getItem(int index) {
        return array[index];
    }


    private void growArraySize() {
        Object[] newArray = new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        this.array = newArray;
    }
}
