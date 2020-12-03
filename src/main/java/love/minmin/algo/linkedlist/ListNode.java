package love.minmin.algo.linkedlist;

import lombok.ToString;

/**
 * Created by IntelliJ IDEA.
 * User: zhoutianbin
 * Date: 2020-12-03
 * Time: 22:03
 */
@ToString
public class ListNode {
    int val;
    ListNode next;
    public ListNode(int val) {
        this.val = val;
    }
}
