package com.example.intuitcraftround.controller;

import com.example.intuitcraftround.exceptions.NodeNotFoundException;
import com.example.intuitcraftround.model.Node;
import com.example.intuitcraftround.service.TreeConstructorService;
import com.example.intuitcraftround.service.impl.NodeRemovalServiceImpl;
import com.example.intuitcraftround.view.NodeView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class TreeController {

    @Autowired
    NodeRemovalServiceImpl nodeRemovalService;

    @Autowired
    NodeView nodeView;

    @Autowired
    TreeConstructorService treeConstructorService;

   public Node constructTree(String data) {
       try {
           return treeConstructorService.constructTree(data);
       } catch (IllegalArgumentException ex) {
           log.error("IllegalArgumentException while constructing tree {}", ex.getMessage());
       } catch (Exception ex) {
           log.error("Exception occurred while constructing tree {}", ex.getMessage());
           throw ex;
       }
       return null;
   }

    public List<Integer> removeNode(Node root, int nodeToBeRemoved, String removalStrategy) {
       List<Integer> IdList = new ArrayList<>();
       try {
           List<Node> subTreeNodeList = nodeRemovalService.removeNode(root, nodeToBeRemoved, removalStrategy);
           IdList =  nodeView.getIdView(subTreeNodeList);
       } catch (NodeNotFoundException ex) {
           log.error("NodeNotFoundException in removeNode:{} ", ex.getMessage());
       } catch(Exception ex) {
           log.error("Exception in removeNode: {} ", ex.getMessage());
       }
       return IdList;
    }
}
