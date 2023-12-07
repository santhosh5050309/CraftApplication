package com.example.intuitcraftround.service;

import com.example.intuitcraftround.exceptions.NodeNotFoundException;
import com.example.intuitcraftround.model.Node;

import java.util.List;

public interface NodeRemovalService {
    List<Node> removeNode(Node root, int nodeToBeRemoved) throws NodeNotFoundException;
    List<Node> removeNode(Node root, int nodeToBeRemoved, String criteria) throws NodeNotFoundException;
}
