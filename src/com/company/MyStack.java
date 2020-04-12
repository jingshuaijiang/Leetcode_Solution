package com.company;

import java.util.LinkedList;

public class MyStack {
    LinkedList<Integer> list;
    public MyStack() {
        list = new LinkedList();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        list.push(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {

        return list.pollFirst();
    }

    /** Get the top element. */
    public int top() {
        int c = list.pollFirst();
        list.push(c);
        return c;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return list.size()==0;
    }
}
