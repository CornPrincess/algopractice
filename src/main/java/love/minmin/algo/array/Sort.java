package love.minmin.algo.array;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: zhoutianbin
 * Date: 2020-12-04
 * Time: 01:34
 */
public class Sort {
    public static void bubbleSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        // 提前退出冒泡循环的标志位
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int t = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = t;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    public static void insertSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        // 从1 开始，因为一开始索引0处是认为已经排好序的，i表示未排序区域的第一个位置
        for (int i = 1; i < n; i++) {
            int value = a[i];
            // 这里 j 取的是已排序区域的最后一个位置
            int j = i - 1;
            // 查找插入位置，遍历已查找区域，为value挪位置
            for (; j >= 0; j--) {
                if (a[j] > value) {
                    a[j+1] = a[j];
                } else {
                    break;
                }
            }
            // 插入数据
            a[j+1] = value;
        }
    }

    public static void selectSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[i]) {
                    int t = a[i];
                    a[i] = a[j];
                    a[j] = t;
                }
            }
        }
    }

    public static void mergeSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        mergeSort0(a, 0, n-1);
    }

    public static void mergeSort0(int[] a, int l, int r){
        // 递归终止条件
        if (l >= r) {
            return;
        }

        int q = (l + r) / 2;
        mergeSort0(a, l , q);
        mergeSort0(a, q+1 , r);
        merge(a, l, r, q);
    }

    public static void merge(int[] a, int l, int r, int  q) {
        int i = l;
        int j = q + 1;
        int k = 0;

        int[] temp = new int[r - l + 1];
        while (i <= q && j <= r) {
            if  (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        int start, end;
        if (i <= q) {
            start = i;
            end = q;
        } else {
            start = j;
            end = r;
        }

        while (start <= end) {
            temp[k++] = a[start++];
        }

        for (int p = 0; p < r-l+1; p++) {
            a[p++] = temp[p++];
        }
    }

    public static void quickSort(int[] a, int n) {
        quickSort0(a, 0, n-1);
    }

    public static void quickSort0(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }

        int q = partition(a, p, r);
        quickSort0(a, p, q-1);
        quickSort0(a,q+1, r);
    }

    public static int partition(int[] a, int p, int r) {
        int pivot = a[r];
        int i = p;
        for (int j = p; j < r; j++) {
            if (a[j] < pivot) {
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
                i++;
            }
        }
        int t = a[i];
        a[i] = a[r];
        a[r] = t;
        return i;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 4, 2, 74, 5, 891, 96, 18, 1, 89, 16, 498, 5};
//        bubbleSort(a, a.length);
//        insertSort(a, a.length);
//        selectSort(a, a.length);
        mergeSort(a, a.length);
        System.out.println(Arrays.toString(a));
    }
}
