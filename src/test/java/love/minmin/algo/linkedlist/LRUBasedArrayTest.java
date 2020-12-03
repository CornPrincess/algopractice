package love.minmin.algo.linkedlist;

import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: zhoutianbin
 * Date: 2020-11-18
 * Time: 21:56
 */
public class LRUBasedArrayTest {

    @Test(expected = IllegalArgumentException.class)
    public void test_exception() {
        LRUBasedArray<Integer> lru = new LRUBasedArray<Integer>();
        lru.offer(null);
    }

    @Test
    public void test_DefaultConstructor() {
        System.out.println("======无参测试========");
        LRUBasedArray<Integer> lru = new LRUBasedArray<Integer>();
        lru.offer(1);
        lru.offer(2);
        lru.offer(3);
        lru.offer(4);
        lru.offer(5);
        System.out.println(lru);
        lru.offer(6);
        lru.offer(7);
        lru.offer(8);
        lru.offer(9);
        System.out.println(lru);
    }

    @Test
    public void test_SpecialConstructor() {
        System.out.println("======有参测试========");
        LRUBasedArray<Integer> lru = new LRUBasedArray<Integer>(4);
        lru.offer(1);
        System.out.println(lru);
        lru.offer(2);
        System.out.println(lru);
        lru.offer(3);
        System.out.println(lru);
        lru.offer(4);
        System.out.println(lru);
        lru.offer(2);
        System.out.println(lru);
        lru.offer(4);
        System.out.println(lru);
        lru.offer(7);
        System.out.println(lru);
        lru.offer(1);
        System.out.println(lru);
        lru.offer(2);
        System.out.println(lru);
    }
}
