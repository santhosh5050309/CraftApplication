package com.example.intuitcraftround.service.impl;

import com.example.intuitcraftround.service.NodeRemovalStrategy;
import com.example.intuitcraftround.service.NodeRemovalStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NodeRemovalStrategyFactoryImpl implements NodeRemovalStrategyFactory {

    @Autowired
    @Qualifier("nodeRemovalByLevelOrderStrategy")
    private NodeRemovalStrategy levelOrderStrategy;

    @Autowired
    @Qualifier("nodeRemovalByRecursiveStrategy")
    private NodeRemovalStrategy recursiveStrategy;

    public NodeRemovalStrategy getStrategy(String strategy) {
        return switch (strategy) {
            case "nodeRemovalByLevelOrderStrategy" -> levelOrderStrategy;
            case "nodeRemovalByRecursiveStrategy" -> recursiveStrategy;
            default -> levelOrderStrategy;
        };
    }
}
