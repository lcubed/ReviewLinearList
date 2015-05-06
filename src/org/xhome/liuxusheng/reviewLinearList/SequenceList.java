package org.xhome.liuxusheng.reviewLinearList;

import java.util.Arrays;

/**
 * Created by liuxusheng on 15-5-5.
 */
public class SequenceList<T> {
    private final int DEFAULT_SIZE = 16;
    private int capacity;
    private Object[] elementData;
    private int size = 0;

    public SequenceList() {
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }

    public SequenceList(T element) {
        this();
        this.elementData[0] = element;
        size++;
    }

    public SequenceList(T element, int initSize) {
        capacity = 1;
        while (capacity < initSize) {
            capacity <<= 1;
        }

        elementData = new Object[capacity];
        this.elementData[0] = element;
        size++;
    }

    public int length() {
        return size;
    }

    public T get(int index) {
        checkIndex(index);
        return (T) elementData[index];
    }

    public int locate(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public void insert(T element, int index) {
        checkIndex(index);
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Linear List index out of bounds!");
        }
    }

    public void add(T element) {
        insert(element, size);
    }

    private void ensureCapacity(int minCapacity) {
        if (capacity < minCapacity) {
            while (capacity < minCapacity) {
                capacity <<= 1;
            }
        }
        elementData = Arrays.copyOf(elementData, capacity);
    }

    public T delete(int index) {
        checkIndex(index);
        T element = (T) elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        }
        elementData[--size] = null;
        return element;
    }

    public T remove() {
        return delete(size - 1);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        Arrays.fill(elementData, null);
        size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("[");
            for (int i = 0; i < size; i++) {
                stringBuffer.append(elementData[i].toString());
            }
            int length = stringBuffer.length();
            stringBuffer.delete(length - 2, length).append("]");
            return stringBuffer.toString();
        }
    }
}
