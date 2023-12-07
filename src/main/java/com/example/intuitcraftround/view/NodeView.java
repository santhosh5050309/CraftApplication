package com.example.intuitcraftround.view;

import com.example.intuitcraftround.model.Node;
import org.springframework.stereotype.Component;

import java.util.List;

public interface NodeView {
    List<Integer> getIdView(List<Node> nodeList);
}
