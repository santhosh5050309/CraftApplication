package com.example.intuitcraftround.service.impl;

import com.example.intuitcraftround.model.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeConstructorServiceImplTest {
    private TreeConstructorServiceImpl service;
    @BeforeEach
    void setUp() {
        service = new TreeConstructorServiceImpl();
    }

    @Test
    public void testConstructTreeValidInput() {
        String input = "1,2,#,#,3,#,#";
        Node root = service.constructTree(input);
        assertNotNull(root);
        assertEquals(1, root.getId());
        assertEquals(2, root.getLeft().getId());
        assertNull(root.getLeft().getLeft());
        assertNull(root.getLeft().getRight());
        assertEquals(3, root.getRight().getId());
    }

    @Test
    public void testConstructTreeInvalidInputSpecialCharacter() {
        String input = "1,2,x,#,3,#,#";
        assertThrows(IllegalArgumentException.class, () -> {
            service.constructTree(input);
        });
    }

    @Test
    public void testConstructTreeInvalidInputDuplicateData() {
        String input = "1,2,#,#,2,#,#";
        assertThrows(IllegalArgumentException.class, () -> {
            service.constructTree(input);
        });
    }

}