package LinkedList;
// for singly-linked list.

public class Lec_001 {
    
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // It return "FIRST" mid node in even Linked List
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    // It return "SECOND" mid node in even Linked List : NOT USED
    public ListNode middleNodeSecond(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    public ListNode reverseLinkedList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = null, curr = head;

        while (curr != null) {
            ListNode forw = curr.next; // back up

            curr.next = prev; // link

            prev = curr;
            curr = forw;
        }
        return prev;
    }

    public int lengthLinkedList(ListNode head) {
        if (head == null)
            return 0;
        ListNode curr = head;
        int length = 1;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        return length;
    }

    // 234
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;

        ListNode mid = middleNode(head);
        mid.next = null;
        ListNode reverseLL = reverseLinkedList(mid.next);

        ListNode forw = head;
        ListNode back = reverseLL;
        boolean res = true;
        while (back != null) {
            if (forw.val != back.val) {
                res = false;
                break;
            }
            forw = forw.next;
            back = back.next;
        }

        mid.next = reverseLinkedList(reverseLL);
        return res;
    }

    // 2
    public ListNode addTwoNumbers(ListNode n1, ListNode n2) {

        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        ListNode revN1 = reverseLinkedList(n1);
        ListNode revN2 = reverseLinkedList(n2);
        ListNode c1 = revN1, c2 = revN2;
        int carry = 0;
        while (c1 != null || c2 != null || carry > 0) {

            int val1 = (c1 != null) ? c1.val : 0;
            int val2 = (c2 != null) ? c2.val : 0;
            int sum = val1 + val2 + carry;
            carry = sum / 10;
            sum = sum % 10;
            ListNode node = new ListNode(sum);
            curr.next = node;
            curr = curr.next;
            if (c1 != null)
                c1 = c1.next;
            if (c2 != null)
                c2 = c2.next;
        }

        ListNode head = dummy.next;
        ListNode newHead = reverseLinkedList(head);
        n1 = reverseLinkedList(revN1);
        n2 = reverseLinkedList(revN2);
        return newHead;
    }

    
    public static void main(String[] args) {

    }
}