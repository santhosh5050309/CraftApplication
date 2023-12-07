package com.example.intuitcraftround.service.impl;

import com.example.intuitcraftround.service.NodeRemovalStrategy;

import static org.junit.jupiter.api.Assertions.*;

class NodeRemovalRecursiveStrategyImplTest  extends NodeRemovalStrategyTestBase {
    @Override
    NodeRemovalStrategy getStrategy() {
        return new NodeRemovalRecursiveStrategyImpl();
    }
}