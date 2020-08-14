package com.company;
import java.util.*;

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











}
