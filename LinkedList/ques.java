public class ques{

    //Q1-middle of linkedlist
    public static ListNode midNode(ListNode head) {
        if(head==null ||head.next==null)
        return null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
          slow = slow.next;
          fast = fast.next.next;
        }
        return slow;
      }
}
public static boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null)
        return true;

    ListNode mid = midNode(head);
    ListNode nhead = mid.next;
    mid.next = null;
    nhead = reverse(nhead);

    boolean flag = true;
    ListNode c1 = head, c2 = nhead;
    while (c1 != null && c2 != null) {
        if (c1.val != c2.val) {
            flag = false;
            break;
        }

        c1 = c1.next;
        c2 = c2.next;
    }

    nhead = reverse(nhead);
    mid.next = nhead;

    return flag;
}

//FOLD THE LINKED LIST
public static void fold(ListNode head) {
    if (head == null || head.next == null)
        return;

    ListNode mid = midNode(head);
    ListNode nhead = mid.next;
    mid.next = null;
    nhead = reverse(nhead);

    ListNode c1 = head, c2 = nhead;

    while (c2 != null) {
        ListNode f1 = c1.next, f2 = c2.next;

        c1.next = c2;
        c2.next = f1;

        c1 = f1;
        c2 = f2;
    }
}

//UNFOLD THE LINKEDLIST 
public static void unfold(ListNode head) {
    if (head == null || head.next == null)
      return ;
    ListNode c1 = head, c2 = head.next, nhead = head.next;
    while (c2 != null && c2.next != null) {
      ListNode f = c2.next;

      c1.next = f;
      c2.next = f.next;

      c1 = c1.next;
      c2 = c2.next;
    }
    nhead = reverse(nhead);
    c1.next = nhead;
    return ;
  }

  //MERGE TWO SORTED LINKEDLIST
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if(l1==null)
        return l2;
    if(l2==null)
        return l1;
    ListNode head=new ListNode(-1);
    ListNode curr=head;
    if(l1.val>l2.val){
        curr.val=l2.val;
        l2=l2.next;
    }else{
        curr.val=l1.val;
        l1=l1.next;
    }
    while(l1!=null && l2!=null){
        int data=-1;
        if(l1.val<=l2.val){
            data=l1.val;
            l1=l1.next;
        }
        else{
            data=l2.val;
            l2=l2.next;
        }
           
        ListNode dummy=new ListNode(data);
        curr.next=dummy;
        curr=curr.next;
    }
    while(l1!=null){
        ListNode x=new ListNode(l1.val);
        curr.next=x;
        curr=x;
        l1=l1.next;
    }
    while(l2!=null){
        ListNode x=new ListNode(l2.val);
        curr.next=x;
        curr=x;
        l2=l2.next;
    }
    return head;
}