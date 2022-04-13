package Tasks;
public class April_11th {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // ListNode l1head = reverse(l1);
        // ListNode l2head = reverse(l2);
        
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        ListNode dummy = new ListNode(-1);
        ListNode res = dummy;
        int carry = 0;
        while(temp1 != null || temp2 != null || carry > 0){
            ListNode node = new ListNode();
            node.val = (temp1 == null ? 0 : temp1.val) + (temp2 == null ? 0 : temp2.val) + carry;
            carry = node.val / 10;
            node.val = node.val % 10;
            res.next = node;
            res = res.next;
            if(temp1 != null) temp1 = temp1.next;
            if(temp2 != null) temp2 = temp2.next;
        }
        return dummy.next;
    }
        
    public static ListNode reverse(ListNode head){
        if(head == null || head.next == null) return head;
        
        ListNode curr = head.next;
        ListNode prev = head;
        while(curr != null){
            ListNode forw = curr.next;
            curr.next = prev;
            prev = curr;
            curr = forw;
        }
        head.next = null;
        return prev;
    }


    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || head.next == null) return null;
        int i = 0;
        ListNode fast = head;
        ListNode prev = null;
        while(i++ < n && fast != null){
            fast = fast.next;
        }
        if(fast == null){
            return head.next;
        }
        
        ListNode slow = head;        
        while(fast != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next;
        }
        
        prev.next = slow.next;
        slow = null;
        return head;
    }
}
