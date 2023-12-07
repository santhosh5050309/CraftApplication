package com.example.intuitcraftround.view.impl;

import com.example.intuitcraftround.model.Node;
import com.example.intuitcraftround.view.NodeView;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class NodeViewImpl implements NodeView {
    public List<Integer> getIdView(List<Node> nodeList) {
        List<Integer> nodeIdlist = new ArrayList<>();
        for(Node node: nodeList) {
            if(node != null) {
                nodeIdlist.add(node.getId());
            }
        }
        return nodeIdlist;
    }
}
