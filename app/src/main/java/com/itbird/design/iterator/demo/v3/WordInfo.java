package com.itbird.design.iterator.demo.v3;

import com.itbird.design.iterator.demo.v3.iterator.Iterator;
import com.itbird.design.iterator.demo.v3.iterator.NodeIterator;

/**
 * word文档内容
 * Created by itbird on 2022/7/9
 */
public class WordInfo {
    String title;
    Node node;
    NodeIterator<Node> nodeNodeIterator;

    public WordInfo() {
        nodeNodeIterator = new NodeIterator<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Node getNode() {
        return node;
    }

    public NodeIterator<Node> getNodeNodeIterator() {
        return nodeNodeIterator;
    }

    public void setNode(Node node) {
        this.node = node;
        nodeNodeIterator.setNode(node);
    }

    public static class Node {
        int lineNum;
        String content;
        Node next;

        public int getLineNum() {
            return lineNum;
        }

        public void setLineNum(int lineNum) {
            this.lineNum = lineNum;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

}
