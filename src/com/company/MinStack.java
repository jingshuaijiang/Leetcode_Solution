package com.company;

import java.util.*;

public class MinStack {
    Stack<Integer> minstack;
    Stack<Integer> normalstack;

    public MinStack() {
        minstack  = new Stack<>();
        normalstack = new Stack<>();

    }

    public void push(int x) {
        normalstack.push(x);
        if(minstack.isEmpty() || x <=minstack.peek())
            minstack.push(x);
    }

    public void pop() {
        int c = normalstack.pop();
        if(c == minstack.peek())
            minstack.pop();
    }

    public int top() {
        return normalstack.peek();
    }

    public int getMin() {
        return minstack.peek();
    }
}
