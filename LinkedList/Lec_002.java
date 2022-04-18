public class Lec_002 {
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static int length(ListNode head){
        if(head == null){
            return 0;
        }
        int n = 0;
        while(head != null){
            head = head.next;
            n++;
        }
        return n;
    }

    public static ListNode reverse(ListNode head){
        if(head == null || head.next == null) return head;

        ListNode prev = null;
        ListNode curr = head;
        while(curr != null){
            ListNode forw = curr.next;
            curr.next = prev;

            prev = curr;
            curr = forw;
        }

        return prev;
    }

    public static boolean isBigger(ListNode l1, ListNode l2){
        int len1 =  length(l1);
        System.out.println(len1);
        int len2 = length(l2);
        System.out.println(len2);

        if(len1 == len2){
            ListNode c1 = l1, c2 = l2;
            while(c1 != null){
                if(c1.val < c2.val) return false;
                else if(c1.val > c2.val) return true;
                c1 = c1.next;
                c2 = c2.next;
            }
            if(c1 == null) return false;
        }

        return len1 - len2 > 0 ? true : false;
    }

    // Remove Duplicates from Sorted Linked List
    public static ListNode removeDuplicates(ListNode head){
        if(head == null || head.next == null) return head;

        ListNode prev = new ListNode((int)1e9 * -1);
        ListNode curr = head;
        while(curr != null){
            if(prev.val == curr.val){
                ListNode forw = curr.next;
                prev.next = forw;
                curr.next = null;
                curr = forw;
            } else{
                prev = curr;
                curr = curr.next;
            }
        }
        return head;
    }

    public static void printList(ListNode head){
        if(head == null) return;
        ListNode curr = head;
        while(curr != null){
            System.out.print(curr.val + " " );
            curr = curr.next;
        }
        System.out.println();
    }

    // 328.
    public static ListNode oddEvenList(ListNode head){
        if(head == null || head.next == null) return head;

        ListNode dummy = new ListNode(-1);
        ListNode odd = dummy, even = new ListNode(-1);
        ListNode oddPtr = odd, evenPtr = even;
        ListNode curr = head;
        int count = 1;
        while(curr != null){
            if(count % 2 != 0){
                oddPtr.next = curr;
                curr = curr.next;
                oddPtr = oddPtr.next;
                oddPtr.next = null;
            } else{
                evenPtr.next = curr;
                curr = curr.next;
                evenPtr = evenPtr.next;
                evenPtr.next = null;
            }
            count++;
        }
        oddPtr.next = even.next;
        return odd.next;
    }
    
    public static void main(String[] args) {
        ListNode head1 = null;
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        head1 = node;
        
        ListNode head2 = null;
        ListNode temp = new ListNode(4);
        temp.next = new ListNode(5);
        temp.next.next = new ListNode(6);
        // head2 = temp;
        node.next.next.next = temp;

        // System.out.println(isBigger(head2, head1));
        // ListNode head = removeDuplicates(head1);
        printList(head1);
        ListNode head = oddEvenList(head1);
        printList(head);
    }

    
}
