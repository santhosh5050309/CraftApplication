package com.example.intuitcraftround.service.impl;

import com.example.intuitcraftround.exceptions.NodeNotFoundException;
import com.example.intuitcraftround.model.Node;
import com.example.intuitcraftround.service.NodeRemovalService;
import com.example.intuitcraftround.service.NodeRemovalStrategy;
import com.example.intuitcraftround.service.NodeRemovalStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeRemovalServiceImpl implements NodeRemovalService {

    @Autowired
    private NodeRemovalStrategyFactory strategyFactory;

    public List<Node> removeNode(Node root, int nodeToBeRemoved, String removalStrategy) throws NodeNotFoundException {
        NodeRemovalStrategy strategy = strategyFactory.getStrategy(removalStrategy);
        return strategy.removeNode(root, nodeToBeRemoved);
    }

    public List<Node> removeNode(Node root, int nodeToBeRemoved) throws NodeNotFoundException {
        NodeRemovalStrategy strategy = strategyFactory.getStrategy("");
        return strategy.removeNode(root, nodeToBeRemoved);
    }
}
