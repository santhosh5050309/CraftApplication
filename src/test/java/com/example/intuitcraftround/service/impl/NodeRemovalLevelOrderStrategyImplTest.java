package com.example.intuitcraftround.service.impl;

import com.example.intuitcraftround.service.NodeRemovalStrategy;

class NodeRemovalLevelOrderStrategyImplTest extends NodeRemovalStrategyTestBase {
    @Override
    NodeRemovalStrategy getStrategy() {
        return new NodeRemovalLevelOrderStrategyImpl();
    }
}