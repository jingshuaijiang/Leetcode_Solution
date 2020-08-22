package com.company;
import java.util.*;

/**
 * Skills to solve the linkedlist questions.
 * 1. If it is from the front to back. Then it is easy.
 *      Use recursive or iterative functions.
 *     Be careful when you are writing iterative functions. Think clearly about the boundry conditions before you write.
 *  Recursive: Leetcode 21,2
 *  Iterative: almost every problem
 *
 * 2. Using fast and slow pointer to get the half or cycle.
 *  Leetcode: 141
 * 3. reverse the list
 *  Leetcode 206
 * 4. Using the combination of finding the half and reverse the list.
 *  Leetcode 328,143
 * 5. If it is from the back to the front. Try to use stack.
 *      Leetcode 445
 *      Leetcode 1019
 */
public class Solution_Linkedlist {
    public ListNode Num24swapPairs(ListNode head) {
        if(head==null)
            return null;
        ListNode p1 = head;
        ListNode newhead = new ListNode(0);
        newhead.next = head;
        ListNode pre = newhead;
        while(p1!=null)
        {
            if(p1.next==null)
                break;
            ListNode p2 = p1.next;
            ListNode p3 = p2.next;
            p2.next = p1;
            p1.next = p3;
            pre.next = p2;
            pre = p1;
            p1 = p1.next;
        }
        return newhead.next;
    }

    public ListNode Num328oddEvenList(ListNode head) {
        if(head==null)
            return null;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenhead = even;
        while(even!=null||even.next!=null)
        {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenhead;
        return head;
    }

    public ListNode Num328sol2oddEvenList(ListNode head) {
        if(head==null)
            return null;
        ListNode odd = null;
        ListNode even = null;
        ListNode evenhead = null;
        ListNode oddhead = null;
        ListNode p1 = head;
        while(p1!=null)
        {
            ListNode p2 = p1.next;
            if(odd==null)
            {
                odd = p1;
                oddhead = odd;
            }
            else
            {
                odd.next = p1;
                odd = odd.next;
            }
            p1.next = null;
            if(p2==null)
            {
                break;
            }
            if(even==null)
            {
                even = p2;
                evenhead = even;
            }
            else
            {
                even.next = p2;
                even = even.next;
            }
            p1 = p2.next;
            p2.next = null;
        }
        odd.next = evenhead;
        return oddhead;
    }

    public void deleteNode(ListNode node) {
        if(node.next==null)
            node = null;
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public ListNode Num92reverseBetween(ListNode head, int m, int n) {
        if(head==null)
            return null;
        if(m==n)
            return head;
        ListNode node  = new ListNode();
        node.next = head;
        ListNode firstend = node;
        int i=0;
        while(i<m-1)
        {
            firstend = firstend.next;
            i++;
        }

        ListNode  pre = firstend.next;
        ListNode tail = pre;
        ListNode curr = pre.next;
        ListNode third = null;
        i+=2;
        while(i<=n)
        {
            third = curr.next;
            curr.next = pre;
            pre = curr;
            curr = third;
            i++;
        }
        tail.next = curr;
        firstend.next = pre;
        return node.next;
    }

    public ListNode Num203removeElements(ListNode head, int val) {
        if(head==null||(head.next==null&&head.val == val))
            return null;
        ListNode node = new ListNode(-1);
        node.next = head;
        ListNode pre = node;
        ListNode curr = head;
        while(curr!=null)
        {
            if(curr.val==val)
            {
                pre.next = curr.next;
                curr = pre.next;
            }
            else {
                pre = pre.next;
                curr = curr.next;
            }
        }
        return node.next;
    }

    public ListNode Num369plusOne(ListNode head) {
        ListNode node = new ListNode(0);
        node.next = head;
        ListNode closest_9 = node;
        ListNode curr = head;
        ListNode pre = node;
        int pre_9 = 0;
        while(curr.next!=null)
        {
            if (curr.val == 9)
            {
                pre_9++;
                if (pre_9 == 1)
                    closest_9 = pre;
            }
            else
            {
                if(pre_9>0)
                {
                    pre_9 = 0;
                }
                closest_9 = curr;
            }
            pre = curr;
            curr = curr.next;
        }

        if(curr.val<9)
            curr.val+=1;
        else
        {
            closest_9.val+=1;
            while(closest_9.next!=null)
            {
                closest_9 = closest_9.next;
                closest_9.val = 0;
            }
        }
        return node.val==1?node:node.next;
    }

    public ListNode Num82deleteDuplicates(ListNode head) {
        if(head==null)
            return null;
        ListNode node = new ListNode();
        node.next = head;
        ListNode ppre = node;
        ListNode pre = head;
        ListNode curr = head.next;
        int should_delete = 0;
        while(curr!=null)
        {
            if(pre.val==curr.val)
            {
                should_delete++;
            }
            else
            {
                if(should_delete==0)
                {
                    ppre.next = pre;
                    ppre = ppre.next;
                    ppre.next = null;
                }
                should_delete = 0;
            }
            pre = curr;
            curr = curr.next;
        }
        if(should_delete==0)
        {
            ppre.next = pre;
            ppre = ppre.next;
        }
        ppre.next = null;
        return node.next;
    }

    public ListNode Num2addTwoNumbers(ListNode l1, ListNode l2) {
        int carrier = 0;
        return Num2Helper(l1,l2,carrier);
    }

    public ListNode Num2Helper(ListNode l1,ListNode l2,int carrier)
    {
        if(l1==null&&l2==null)
        {
            if(carrier!=0)
            {
                ListNode node = new ListNode(carrier);
                return node;
            }
            else
                return null;
        }
        int val1 = l1==null?0:l1.val;
        int val2 = l2==null?0:l2.val;
        int curval = val1+val2+carrier;
        int curdig = curval%10;
        ListNode node = new ListNode(curdig);
        int nextcarrier = curval/10;
        if(l1==null)
            node.next = Num2Helper(null,l2.next,nextcarrier);
        else if(l2==null)
            node.next = Num2Helper(l1.next,null,nextcarrier);
        else
            node.next = Num2Helper(l1.next,l2.next,nextcarrier);
        return node;
    }

    public ListNode Num2iterativeaddTwoNumbers(ListNode l1, ListNode l2) {
        int carrier = 0;
        ListNode head = new ListNode(0);
        ListNode p = head;
        while(l1!=null||l2!=null||carrier!=0)
        {
            int val1 = l1==null?0:l1.val;
            int val2 = l2==null?0:l2.val;
            int curval = val1+val2+carrier;
            int curdig = curval%10;
            carrier = curval/10;
            ListNode node = new ListNode(curdig);
            head.next = node;
            head = head.next;
            l1 = l1==null?null:l1.next;
            l2 = l2==null?null:l2.next;
        }
        return p.next;
    }

    public void Num143reorderList(ListNode head) {
        if(head==null||head.next==null)
            return;
        ListNode firsthalf = Num143half(head);
        ListNode reversed_second = Num143reverse(firsthalf.next);
        firsthalf.next = null;
        ListNode firsthead = head, temp = null;
        while(reversed_second!=null)
        {
            temp = firsthead.next;
            firsthead.next = reversed_second;
            firsthead = temp;
            temp = reversed_second.next;
            reversed_second.next = firsthead;
            reversed_second = temp;
        }
    }

    public ListNode Num143half(ListNode node)
    {
        ListNode fast = node;
        ListNode slow = node;
        while(fast.next!=null&&fast.next.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode Num143reverse(ListNode node)
    {
        ListNode pre = node;
        ListNode curr= node.next;
        pre.next = null;
        ListNode third = null;
        while(curr!=null)
        {
            third = curr.next;
            curr.next = pre;
            pre = curr;
            curr = third;
        }
        return pre;
    }

    public int[] Num1019nextLargerNodes(ListNode head) {
        if(head==null)
            return null;
        List<Integer> ans = new ArrayList<>();
        Stack<ListNode> stack = new Stack<>();
        Stack<Integer> indexstack = new Stack<>();
        int i = 0;
        while(head!=null)
        {
            int value = head.val;
            while(!stack.isEmpty()&&stack.peek().val<value)
            {
                int index = indexstack.pop();
                stack.pop();
                ans.set(index,value);
            }
            if(head!=null)
            {
                indexstack.push(i);
                stack.push(head);
                ans.add(0);
            }
            i++;
            head = head.next;
        }
        while(!stack.isEmpty())
        {
            int index = indexstack.pop();
            ans.set(index,0);
            stack.pop();
        }
        int[] ansa = new int[ans.size()];
        for(int j=0;j<ans.size();j++)
        {
            ansa[j] = ans.get(j);
        }
        return ansa;
    }

    public ListNode Num445addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        while(l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while(l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        ListNode res = null;
        int c = 0;
        while(!s1.isEmpty()||!s2.isEmpty()||c>0)
        {
            int sum = (s1.isEmpty() ? 0 : s1.pop()) +
                    (s2.isEmpty() ? 0 : s2.pop()) + c;
            ListNode n = new ListNode(sum % 10);
            c = sum / 10;
            n.next = res;
            res = n;
        }
        return res;
    }


    private ListNode head;
    public TreeNode Num109sortedListToBST(ListNode head) {
        if(head==null)
            return null;
        int size = Num109Findlist(head);
        this.head = head;
        return convertlistToBst(0,size-1);
    }

    public int Num109Findlist(ListNode head)
    {
        int size = 0;
        ListNode p1 = head;
        while(p1!=null)
        {
            p1 = p1.next;
            size++;
        }
        return size;
    }

    public TreeNode convertlistToBst(int l, int r)
    {
        if(l>r)
            return null;
        int mid = (l+r)/2;

        TreeNode left = convertlistToBst(l,mid-1);
        TreeNode node = new TreeNode(this.head.val);
        node.left = left;
        this.head = this.head.next;
        node.right = this.convertlistToBst(mid+1,r);
        return node;
    }

    public Node Num426treeToDoublyList(Node root) {

    }











































}
