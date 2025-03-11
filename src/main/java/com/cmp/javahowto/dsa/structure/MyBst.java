package com.cmp.javahowto.dsa.structure;

public class MyBst {

    private Node root;

    public boolean insert(int data) {
        if (root == null) {
            root = new Node(data);
            return true;
        } else {
            return insert(root, data);
        }
    }

    private boolean insert(Node node, int data) {
        if (data < node.data) {
            System.out.println("going left");
            if (node.left == null) {
                node.left = new Node(data);
                return true;
            } else {
                return insert(node.left, data);
            }
        } else if (node.data < data) {
            System.out.println("going right");
            if (node.right == null) {
                node.right = new Node(data);
                return true;
            } else {
                return insert(node.right, data);
            }
        } else {
            return false;
        }
    }

    private static class Node {
        private int data;
        private Node left;
        private Node right;

        private Node(int data) {
            this.data = data;
            System.out.println("created node " + data);
        }
    }

}
