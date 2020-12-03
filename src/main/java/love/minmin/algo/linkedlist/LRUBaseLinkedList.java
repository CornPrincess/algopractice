package love.minmin.algo.linkedlist;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: zhoutianbin
 * Date: 2020-11-18
 * Time: 22:03
 */
public class LRUBaseLinkedList<T> {
    // default capacity
    private final static Integer DEFAULT_CAPACITY = 10;
    // head node
    private SNode<T> headNode;
    // length of linkedlist
    private Integer length;
    // capacity of linkedlist
    private Integer capacity;

    public LRUBaseLinkedList() {
        this.headNode = new SNode<>();
        this.capacity = DEFAULT_CAPACITY;
        this.length = 0;
    }

    public LRUBaseLinkedList(Integer capacity) {
        this.headNode = new SNode<>();
        this.capacity = capacity;
        this.length = 0;
    }

    public void add(T data) {
        SNode preNode = findPreNode(data);

        // if linkedlist contains data
        if (preNode != null) {
            deleteElemOptim(preNode);
            insertElemAtBegin(data);
        } else {
            // if  linkedlist is full
            if (length >= this.capacity) {
                deleteElemAtEnd();
            }
            insertElemAtBegin(data);
        }
    }

    private void deleteElemOptim(SNode preNode) {
        SNode temp = preNode.getNext();
        preNode.setNext(temp.getNext());
        temp = null;
        length--;
    }

    private void insertElemAtBegin(T data) {
        SNode first =  headNode.getNext();
        headNode.setNext(new SNode(data, first));
        length++;
    }

    private SNode findPreNode(T data) {
        SNode node = headNode;
        while (node.getNext() != null) {
            if (data.equals(node.getNext().getElement())) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    private void deleteElemAtEnd() {
        SNode ptr = headNode;
        // linkedlist is empty
        if (ptr.getNext() == null) {
            return;
        }

        while (ptr.getNext().getNext() != null) {
            ptr = ptr.getNext();
        }

        ptr.setNext(null);
        length--;
    }

    private void printAll() {
        SNode sNode = headNode.getNext();
        while (sNode != null) {
            System.out.print(sNode.getElement() + ",");
            sNode = sNode.getNext();
            SNode s = sNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LRUBaseLinkedList list = new LRUBaseLinkedList();
        Scanner sc = new Scanner(System.in);
        while (true) {
            list.add(sc.nextInt());
            list.printAll();
        }
    }


    public class SNode<T> {
        private T element;
        private SNode next;

        public SNode(T element) {
            this.element = element;
        }

        public SNode(T element, SNode next) {
            this.element = element;
            this.next = next;
        }

        public SNode() {
            this.next = null;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public SNode getNext() {
            return next;
        }

        public void setNext(SNode next) {
            this.next = next;
        }
    }
}
