package com.example.intuitcraftround.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Node {
    private Node left;

    private Node right;

    private Integer id;
}
