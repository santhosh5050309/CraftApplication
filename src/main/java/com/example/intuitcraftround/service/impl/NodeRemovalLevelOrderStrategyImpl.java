package com.example.intuitcraftround.service.impl;

import com.example.intuitcraftround.exceptions.NodeNotFoundException;
import com.example.intuitcraftround.model.Node;
import com.example.intuitcraftround.service.NodeRemovalStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Qualifier("nodeRemovalByLevelOrderStrategy")
@Slf4j
public class NodeRemovalLevelOrderStrategyImpl implements NodeRemovalStrategy {

    /*
     * Remove given node from tree using level order traversal
     */
    public List<Node> removeNode(Node root, int nodeToBeRemoved) throws NodeNotFoundException {
        log.info("Removing Node with ID: " + nodeToBeRemoved);
        if(root == null) {
           throw new NodeNotFoundException("Cannot Remove Node Id:" + nodeToBeRemoved + " From an Empty Tree");
        }
        Queue<Node> queue = new ArrayDeque<>();
        List<Node> subTreeList = new ArrayList<>();
        boolean nodeFound = false;

        if (root.getId() == nodeToBeRemoved) {
            nodeFound = true;
            addNodeToList(subTreeList, root.getLeft());
            addNodeToList(subTreeList, root.getRight());
            root.setLeft(null);
            root.setRight(null);
        } else {
            queue.offer(root);
            subTreeList.add(root);
        }

        while (!queue.isEmpty() && !nodeFound) {
            Node node = queue.poll();
            nodeFound = processChild(node, node.getLeft(), nodeToBeRemoved, subTreeList, queue, true) ||
                    processChild(node, node.getRight(), nodeToBeRemoved, subTreeList, queue, false);
        }

        if(!nodeFound) {
            throw new NodeNotFoundException("Cannot find Node with ID: " + nodeToBeRemoved);
        }
        return subTreeList;
    }

    private boolean processChild(Node parent, Node child, int nodeToBeRemoved, List<Node> subTreeList,
                                 Queue<Node> queue, boolean isLeft) {
        if (child != null) {
            if (child.getId() == nodeToBeRemoved) {
                if (isLeft) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
                addNodeToList(subTreeList, child.getLeft());
                addNodeToList(subTreeList, child.getRight());
                child.setLeft(null);
                child.setRight(null);
                return true;
            } else {
                queue.add(child);
            }
        }
        return false;
    }

    private void addNodeToList(List<Node> subTreeList, Node node) {
        if (node != null) {
            subTreeList.add(node);
        }
    }
}
