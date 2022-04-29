package LinkedList;

public class Lec_003 {
    static class ListNode {
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

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode forw = curr.next;
            curr.next = prev;

            prev = curr;
            curr = forw;
        }

        return prev;
    }

    // Tried by Me
    public ListNode reverseKGroup(ListNode head, int k) {

        if (head == null || head.next == null || k == 1)
            return head;
        ListNode curr = head;

        while (curr != null) {
            int i = k;
            ListNode temp = curr;
            while (i-- > 0 && temp != null) {

            }
            if (i > 0) {
                return head;
            }
            ListNode forw = temp.next;
            curr = curr.next;

            // Code incomplete
        }
        return head;
    }

    private static ListNode th = null, tt = null;

    private static void addFirst(ListNode node) {
        if (th == null) {
            th = tt = node;
        } else {
            node.next = th;
            th = node;
        }
    }

    private static int length(ListNode head) {
        if (head == null) {
            return 0;
        }
        int n = 0;
        while (head != null) {
            head = head.next;
            n++;
        }
        return n;
    }

    public static ListNode middleNode(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    // 21
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null || l2 == null)
            return l1 == null ? l2 : l1;
        ListNode dummy = new ListNode(-1), c1 = l1, c2 = l2, prev = dummy;
        while (c1 != null && c2 != null) {
            if (c1.val <= c2.val) {
                prev.next = c1;
                c1 = c1.next;
            } else {
                prev.next = c2;
                c2 = c2.next;
            }
            prev = prev.next;
        }

        prev.next = c1 == null ? c2 : c1;
        ListNode head = dummy.next;
        dummy.next = null;
        return head;
    }

    // 148
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode mid = middleNode(head), nHead = mid.next;
        mid.next = null;
        return mergeTwoLists(sortList(head), sortList(nHead));
    }

    // 23
    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        return mergeKLists(lists, 0, n - 1);
    }

    private ListNode mergeKLists(ListNode[] lists, int si, int ei) {
        if (si >= ei)
            return si > ei ? null : lists[si];
        int mid = (si + ei) / 2;
        return mergeTwoLists(mergeKLists(lists, si, mid), mergeKLists(lists, mid + 1, ei));
    }

    // 25
    public static ListNode reverseKGroup_(ListNode head, int k) {
        if (head == null || head.next == null || k == 1)
            return head;

        int len = length(head);

        ListNode ah = null, at = null, curr = head;
        while (len >= k) {
            int tempK = k;
            while (tempK-- > 0) {
                ListNode forw = curr.next;
                curr.next = null;
                addFirst(curr);
                curr = forw;
            }

            if (ah == null) {
                ah = th;
                at = tt;
            } else {
                at.next = th;
                at = tt;
            }

            th = tt = null;
            len -= k;
        }

        at.next = curr;
        return ah;
    }

    // 92
    public ListNode reverseBetween(ListNode head, int n, int m) {
        int idx = 1;
        ListNode prev = null, curr = head;
        while (curr != null) {
            while (curr != null && idx >= n && idx <= m) {
                ListNode forw = curr.next;
                curr.next = null;
                addFirst(curr);
                curr = forw;
                idx++;
            }

            if (idx > m) {
                if (prev != null) {
                    prev.next = th;
                    tt.next = curr;
                    return head;
                } else {
                    tt.next = curr;
                    return th;
                }
            }

            idx++;
            prev = curr;
            curr = curr.next;
        }
        return head;
    }

    // 1171. Given Tasks

    // 138. Given Tasks

    // 141
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                break;
        }
        return slow == fast;
    }

    // 142
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                break;
        }

        if (slow != fast)
            return null;

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;

        int lenA = length(headA);
        int lenB = length(headB);

        ListNode curr1 = lenA > lenB ? headA : headB;
        int count = lenA > lenB ? lenA - lenB : lenB - lenA;
        ListNode ptr = curr1;
        while (count-- > 0) {
            curr1 = curr1.next;
        }

        ListNode curr2 = ptr == headA ? headB : headA;
        while (curr1 != null && curr2 != null && curr1 != curr2) {
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        if (curr1 == null || curr2 == null) {
            return null;
        }
        return curr1;
    }

    private ListNode getTail(ListNode head){
        if(head == null || head.next == null) return head;
        
        ListNode curr = head;
        while(curr.next != null){
            curr = curr.next;
        }
        return curr;
    }
    
    public ListNode getIntersectionNode_(ListNode headA, ListNode headB){
        if(headA == null || headB == null) return null;

        ListNode tail = getTail(headA);
        tail.next = headB;
        ListNode intersectionNode = detectCycle(headA);
        tail.next = null;

        return intersectionNode;
    }
}
