package Tasks;
public class April_11th {
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // 2
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

    // 19
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


    public static ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
        if(l2 == null) return l1;
        if(l1 == null){
            l2.val = -l2.val;
            return l2;
        }
        
        ListNode c1 = reverse(l1);
        ListNode c2 = reverse(l2);
        ListNode head = new ListNode(-1);
        ListNode itr = head;
        
        int borrow = 0;
        // Given: l1 linked list is always greater that l2;
        while(c1 != null){
            
            int diff = borrow + c1.val - (c2 != null ? c2.val : 0);
            if(diff < 0){
                borrow = -1;
                diff = diff + 10;
            } else{
                borrow = 0;
            }
            
            itr.next = new ListNode(diff);
            itr = itr.next;
            
            c1 = c1.next;
            if(c2 != null) c2 = c2.next;
            
        }
        return reverse(head.next);
    }
}
