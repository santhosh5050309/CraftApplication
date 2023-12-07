package com.example.intuitcraftround.service.impl;

import com.example.intuitcraftround.exceptions.NodeNotFoundException;
import com.example.intuitcraftround.model.Node;
import com.example.intuitcraftround.service.NodeRemovalStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

abstract class NodeRemovalStrategyTestBase {

    private NodeRemovalStrategy removalService;

    abstract NodeRemovalStrategy getStrategy();

    @BeforeEach
    void setUp() {
        removalService = getStrategy();
    }

    @Test
    void removeNodeWithTwoChildren() throws NodeNotFoundException {
        Node leftChildOfRemovedNode = Node.builder().id(4).build();
        Node rightChildOfRemovedNode = Node.builder().id(5).build();
        Node nodeToBeRemoved = Node.builder().id(3).left(leftChildOfRemovedNode)
                .right(rightChildOfRemovedNode).build();
        Node leftRootChild = Node.builder().id(2).build();
        Node root = Node.builder().id(1).left(leftRootChild).right(nodeToBeRemoved).build();

        List<Node> subtrees = removalService.removeNode(root, 3);
        assertEquals(3, subtrees.size());
    }

    @Test
    void removeNodeWithOneChild() throws NodeNotFoundException {
        Node leftChildOfRemovedNode = Node.builder().id(3).build();
        Node nodeToBeRemoved = Node.builder().id(2).left(leftChildOfRemovedNode).build();
        Node root = Node.builder().id(1).left(nodeToBeRemoved).build();

        List<Node> subtrees = removalService.removeNode(root, 2);
        assertEquals(2, subtrees.size());
    }

    @Test
    void removeLeafNode() throws NodeNotFoundException {
        Node leafNode = Node.builder().id(2).build();
        Node root = Node.builder().id(1).left(leafNode).build();

        List<Node> subtrees = removalService.removeNode(root, 2);
        assertEquals(1, subtrees.size());
    }

    @Test
    void removeRootNodeOnly() throws NodeNotFoundException {
        Node root = Node.builder().id(1).build();

        List<Node> subtrees = removalService.removeNode(root, 1);
        assertEquals(0, subtrees.size());
    }

    @Test
    void removeNodeNonExistingNode() {
        Node left = Node.builder().id(2).build();
        Node right = Node.builder().id(3).build();
        Node root = Node.builder().id(1).left(left).right(right).build();
        assertThrows(NodeNotFoundException.class, () -> {
            removalService.removeNode(root, 4);
        });
    }
}