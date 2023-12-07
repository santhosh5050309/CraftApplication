package com.example.intuitcraftround.service.impl;

import com.example.intuitcraftround.exceptions.NodeNotFoundException;
import com.example.intuitcraftround.model.Node;
import com.example.intuitcraftround.service.NodeRemovalStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("nodeRemovalByRecursiveStrategy")
@Slf4j
public class NodeRemovalRecursiveStrategyImpl implements NodeRemovalStrategy {

    /*
     * Recursive Traversal to remove node from the tree, first searching left and then right
     */
    private boolean removeNode(Node parent, int nodeToBeRemoved, List<Node> subTreeList) {
        if (parent == null) {
            return false;
        }

        Node leftChild = parent.getLeft();
        Node rightChild = parent.getRight();

        if (leftChild != null && leftChild.getId() == nodeToBeRemoved) {
            parent.setLeft(null);
            addNodeToList(subTreeList, leftChild.getLeft());
            addNodeToList(subTreeList, leftChild.getRight());
            leftChild.setLeft(null);
            leftChild.setRight(null);
            parent.setLeft(null);
            return true;
        }

        if (rightChild != null && rightChild.getId() == nodeToBeRemoved) {
            parent.setRight(null);
            addNodeToList(subTreeList, rightChild.getLeft());
            addNodeToList(subTreeList, rightChild.getRight());
            rightChild.setLeft(null);
            rightChild.setRight(null);
            parent.setRight(null);
            return true;
        }

        return removeNode(leftChild, nodeToBeRemoved, subTreeList) ||
                removeNode(rightChild, nodeToBeRemoved, subTreeList);
    }

    public List<Node> removeNode(Node root, int nodeToBeRemoved) throws NodeNotFoundException {
        log.info("Removing Node with ID: " + nodeToBeRemoved);
        if (root == null) {
            throw new NodeNotFoundException("Cannot Remove Node Id:" + nodeToBeRemoved + " From an Empty Tree");
        }

        List<Node> subTreeList = new ArrayList<>();
        if (root.getId() == nodeToBeRemoved) {
            addNodeToList(subTreeList, root.getLeft());
            addNodeToList(subTreeList, root.getRight());
            root.setLeft(null);
            root.setRight(null);
            return subTreeList;
        }

        subTreeList.add(root);
        boolean found = removeNode(root, nodeToBeRemoved, subTreeList);
        if (!found) {
            throw new NodeNotFoundException("Cannot find Node with ID: " + nodeToBeRemoved);
        }

        return subTreeList;
    }

    private void addNodeToList(List<Node> subTreeList, Node node) {
        if (node != null) {
            subTreeList.add(node);
        }
    }
}
