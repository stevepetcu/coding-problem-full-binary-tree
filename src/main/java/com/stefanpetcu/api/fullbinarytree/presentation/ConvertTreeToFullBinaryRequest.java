package com.stefanpetcu.api.fullbinarytree.presentation;

import com.stefanpetcu.api.fullbinarytree.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class ConvertTreeToFullBinaryRequest<T> {
    private T[] nodes;
    private List<TreeNode<T>> treeNodes;

    public void setNodes(T[] nodes) { // Needed for request marshaling magic.
        this.nodes = nodes;

        List<TreeNode<T>> treeNodes = new ArrayList<>();

        for (T node : nodes) {
            if (node == null) {
                treeNodes.add(null);
            } else {
                treeNodes.add(new TreeNode<>(node));
            }
        }

        this.treeNodes = treeNodes;
    }

    public List<TreeNode<T>> getTreeNodes() {
        return treeNodes;
    }

    @Override
    public String toString() {
        return "{" +
                "\"treeNodes\":" + treeNodes.toString() +
                '}';
    }
}
