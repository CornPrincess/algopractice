package love.minmin.algo.array;

/**
 * Created by IntelliJ IDEA.
 * User: zhoutianbin
 * Date: 2020-11-18
 * Time: 21:09
 */
public class Array {
   public int[] data;
   private int n;
   private int count;

   public Array(int capacity) {
       this.data = new int[capacity];
       this.n = capacity;
       this.count = 0;
   }

   public int find(int index) {
       if (index < 0 || index >= count) {
           return -1;
       }
       return data[index];
   }

   public boolean insert(int index, int value) {
       // array is full
       if (count == n) {
           System.out.println("array is full");
           return false;
       }
       // array is not full
       // check the index
       if (index < 0 || index > count) {
           System.out.println("The index is error");
           return false;
       }
       for (int i = count; i > index; i--) {
           data[i] = data[i-1];
       }
       data[index] = value;
       ++count;
       return true;
   }

   public boolean delete(int index) {
       if (index < 0 || index >= count) {
           return false;
       }
       for (int i = index + 1; i < count - 1; i++) {
           data[i-1] = data[i];
       }
       count--;
       return true;
   }

   public void printAll() {
       for (int i = 0; i < count; i++) {
           System.out.println(data[i] + " ");
       }
       System.out.println();
   }

    public static void main(String[] args) {
        Array array = new Array(5);
        array.insert(0, 0);
        array.insert(1, 1);
        array.insert(2, 2);
        array.insert(1, 3);
        array.printAll();
    }
}
