package com.company;
import java.util.*;
public class Node {
    public int val;
    public List<Node> children;
    public Node left;
    public Node right;
    public Node random;
    public Node next;
    public Node parent;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
