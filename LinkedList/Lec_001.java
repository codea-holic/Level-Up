

// for singly-linked list.

public class Lec_001 {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // It return "FIRST" mid node in even Linked List
    public ListNode middleNode(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode slow = head, fast = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    // It return "SECOND" mid node in even Linked List
    public ListNode middleNodeSecond(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }
    
    public ListNode reverseLinkedList(ListNode head){
        if(head == null || head.next.next == null) return head;

        ListNode prev = null, curr = head;

        while(curr != null){
            ListNode forw = curr.next; // back up

            curr.next = prev; // link

            prev = curr;
            curr = forw;
        }
        return prev;
    }

    public int lengthLinkedList(ListNode head){
        if(head == null) return 0;
        ListNode curr = head;
        int length = 1;
        while(curr != null){
            length++;
            curr = curr.next;
        }
        return length;
    }

    public boolean isPalindrome(ListNode head){
        if(head == null || head.next == null) return true;

        ListNode mid = middleNode(head);
        mid.next = null;
        ListNode reverseLL = reverseLinkedList(mid.next);

        ListNode forw = head;
        ListNode back = reverseLL;
        while(back != null){
            if(forw.val != back.val) return false;
            forw = forw.next;
            back = back.next;
        }

        return true;
    }

    public static void main(String[] args) {
        
    }
}
