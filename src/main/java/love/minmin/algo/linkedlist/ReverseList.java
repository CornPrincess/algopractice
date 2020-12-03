package love.minmin.algo.linkedlist;

import java.util.Deque;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: zhoutianbin
 * Date: 2020-12-03
 * Time: 22:02
 */
public class ReverseList {

    // 1.双指针
    public static ListNode reverseListByTowPoints(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode curr = null;
        ListNode pre = head;
        while (pre != null) {
            ListNode temp = pre.next;
            pre.next = curr;
            curr = pre;
            pre = temp;
        }
        return curr;
    }

    public static ListNode reverseListByTowPoints2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode curr = head;
        while (head.next != null) {
            ListNode temp = head.next.next;
            head.next.next = curr;
            curr = head.next;
            head.next = temp;
        }
        return curr;
    }

    /*
        2. 递归算法
        递归算法其实很简单，核心就是一个递推公式：
            1.如果链表第2到最后到节点都已经逆序了，那么只要把后面这段指向前一个节点就可以了
            2.结束条件：当递归到最后一个节点时，会返回，此时为结束条件
     */
    public static ListNode recursiveReverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode res = recursiveReverseList(head.next);
        head.next.next = head;
        head.next = null;
        return res;
    }

    public static ListNode recursiveReverseList2(ListNode head) {
        return recur(head, null);
    }

    private static ListNode recur(ListNode cur, ListNode pre) {
        if (cur == null) return pre;
        ListNode res = recur(cur.next, cur);
        cur.next = pre;
        return res;
    }

    public static ListNode createNode(int num) {
        if (num < 2) {
            return new ListNode(1);
        }

        ListNode head = new ListNode(1);
        ListNode res = head;
        for (int i = 2; i <= num; i++) {
            head.next = new ListNode(i);
            head = head.next;
        }
        return res;
    }

    // 如果存在环，如何判断环的长度呢？方法是，快慢指针相遇后继续移动，直到第二次相遇。两次相遇间的移动次数即为环的长度。
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    // 合并两个有序链表，哨兵的作用
    public static ListNode mergeTowLists(ListNode l1, ListNode l2) {
        ListNode dum = new ListNode(-1);
        ListNode curr = dum;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                dum.next = l1;
                l1 = l1.next;
            } else {
                dum.next = l2;
                l2 = l2.next;
            }
            dum = dum.next;
        }

        dum.next = l1 == null ? l2 : l1;
        return curr.next;
    }

    // 删除链表倒数第 n 个结点
    // 计算链表长度
    // 删除的关键是要找到前继节点
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        ListNode res = head;
        int length = getLength(head);
        int N = length - n - 1;
        for (int i = 0; i < N; i++) {
            head = head.next;
        }
        if (N < 0) {
            res = res.next;
        } else {
            head.next = head.next.next;
        }
        // 这里head 不为 null， 是因为传入getLength函数的是一个引用的拷贝，可以理解为地址的拷贝，对这个拷贝的操作不会影响到 这里的 head 引用
        return res;
    }

    // 借助哨兵节点
    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) {
            return head;
        }

        ListNode dum = new ListNode(-1);
        dum.next = head;
        int length = getLength(head);
        int N = length - n;
        ListNode curr = dum;
        for (int i = 0; i < N; i++) {
            curr = curr.next;
        }
        curr.next = curr.next.next;
        return dum.next;
    }

    // 借助栈
    public static ListNode removeNthFromEnd3(ListNode head, int n) {
        if (head == null) {
            return head;
        }

        ListNode dum = new ListNode(-1);
        dum.next = head;
        Deque<ListNode> stack = new LinkedList<>();
        ListNode curr = dum;
        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }

        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        ListNode pre = stack.peek();
        pre.next = pre.next.next;
        return dum.next;
    }

    // 双指针
    public static ListNode removeNthFromEnd4(ListNode head, int n) {
        if (head == null) {
            return head;
        }

        ListNode dum = new ListNode(-1);
        dum.next = head;
        ListNode first = head;
        ListNode second = dum;

        for (int i = 0; i < n; i++) {
            first = first.next;
        }

        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dum.next;
    }

    public static void deleteNode(ListNode node) {
        if (node == null) {
            return;
        }

        ListNode dum = new ListNode(-1);
        dum.next = node;
        ListNode fast = dum;
        ListNode slow = dum;
        ListNode curr = dum;
        int n = 0;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            n++;
        }
        for (int i = 0; i <n - 1; i++) {
            curr = curr.next;
        }
        curr.next = curr.next.next;
    }

    private static int getLength(ListNode head) {
        int num = 0;
        while (head != null) {
            num++;
            head = head.next;
        }
        return num;
    }

    // ******** util ***********
    public static void print(ListNode head) {
        if (head == null) {
            return;
        }

        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void testReverseList(int num) {
        ListNode head = createNode(num);
        print(head);
//        ListNode reverseHead = reverseListByTowPoints(head);
        ListNode reverseHead = reverseListByTowPoints2(head);
//        ListNode reverseHead = recursiveReverseList(head);
        print(reverseHead);
        System.out.println("-----------------------------");
    }

    public static void testRemove(int num, int n) {
        ListNode head = createNode(num);
        print(head);
//        ListNode after = removeNthFromEnd(head, n);
//        ListNode after = removeNthFromEnd2(head, n);
//        ListNode after = removeNthFromEnd3(head, n);
        ListNode after = removeNthFromEnd4(head, n);
        print(after);
        System.out.println("-----------------------------");
    }

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
//            testReverseList(i);
            testRemove(i, 1);
        }
    }
}
