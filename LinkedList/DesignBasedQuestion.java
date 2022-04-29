
/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */

class MyLinkedList {

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private ListNode head, tail;
    int size;

    public MyLinkedList() {
        head = tail = null;
        size = 0;
    }

    public int get(int index) {
        if (index >= size) {
            return -1;
        }
        ListNode curr = head;
        while (index-- > 0) {
            curr = curr.next;
        }
        return curr.val;
    }

    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
        if (head == null) {
            head = tail = node;
        } else {
            ListNode prev = head;
            node.next = prev;
            head = node;
        }
        size++;
    }

    public void printList() {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public void addAtTail(int val) {
        ListNode node = new ListNode(val);
        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    private ListNode getNodeAt(int index) {
        if (index >= size) {
            return null;
        }
        ListNode curr = head;
        while (index-- > 0) {
            curr = curr.next;
        }
        return curr;
    }

    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        } else if (index == 0) {
            addAtHead(val);
        } else if (index == size) {
            addAtTail(val);
        } else {
            ListNode node = getNodeAt(index - 1);
            ListNode newNode = new ListNode(val, node.next);
            node.next = newNode;
            size++;
        }
    }

    public void deleteAtIndex(int index) {
        if (index >= size) {
            return;
        } else if (index == 0) {
            if (head == tail) {
                head = tail = null;
            } else {
                ListNode forw = head.next;
                head.next = null;
                head = forw;
            }
        } else if (index == size - 1) {

            ListNode curr = head;
            while (curr.next != tail) {
                curr = curr.next;
            }
            curr.next = null;
            tail = curr;
        } else {
            ListNode node = getNodeAt(index - 1);
            ListNode forw = node.next;
            node.next = node.next.next;
            forw = null;
        }
        size--;
    }
}

