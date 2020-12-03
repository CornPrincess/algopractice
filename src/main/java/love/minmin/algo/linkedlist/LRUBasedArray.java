package love.minmin.algo.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: zhoutianbin
 * Date: 2020-11-18
 * Time: 21:34
 * Time complexity O(n)
 * Space complexity O(n)
 * not support cache null
 */
public class LRUBasedArray<T> {
    private static final int DEFAULT_CAPACITY = 1 << 3;

    private int capacity;

    private int count;

    private T[] value;

    private Map<T, Integer> holder;

    public LRUBasedArray() {
        this(DEFAULT_CAPACITY);
    }

    public LRUBasedArray(int capacity) {
        this.capacity = capacity;
        value = (T[]) new Object[capacity];
        count = 0;
        holder = new HashMap<>(capacity);
    }

    // read value
    public void offer(T object) {
        if (object == null) {
            throw new IllegalArgumentException("The container is not support null");
        }
        Integer index = holder.get(object);
        if (index == null) {
            if (isFull()) {
                removeAndCache(object);
            } else {
                cache(object, count);
            }
        } else {
            update(index);
        }
    }

    // update the array if cache is in array
    public void update(int end) {
        T target = value[end];
        rightShift(end);
        value[0] = target;
        holder.put(target, 0);
    }

    //  cache value in array if value is not in array
    public void cache(T object, int end) {
        rightShift(count);
        value[0] = object;
        holder.put(object, 0);
        count++;
    }

    // remove the last value and cache object
    public void removeAndCache(T object) {
        T key = value[--count];
        holder.remove(key);
        cache(object, count);
    }

    public void rightShift(int end) {
        for (int i = end - 1; i >= 0; i--) {
            value[i + 1] = value[i];
            holder.put(value[i], i + 1);
        }
    }

    public boolean isContain(T object) {
        return holder.containsKey(object);
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == capacity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(value[i]);
            sb.append(" ");
        }
        return sb.toString();
    }
}
