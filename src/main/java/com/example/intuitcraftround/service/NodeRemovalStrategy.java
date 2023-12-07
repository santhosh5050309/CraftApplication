package com.example.intuitcraftround.service;

import com.example.intuitcraftround.exceptions.NodeNotFoundException;
import com.example.intuitcraftround.model.Node;

import java.util.List;

public interface NodeRemovalStrategy {
    List<Node> removeNode(Node root, int nodeToBeRemoved) throws NodeNotFoundException;
}
