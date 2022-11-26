package com.stefanpetcu.api.fullbinarytree.application;

import com.stefanpetcu.api.fullbinarytree.domain.TreeNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TreeServiceImpl implements TreeService {
    @SafeVarargs
    @Override
    public final <T> TreeNode<T> buildBinaryTree(TreeNode<T> rootNode, TreeNode<T>... otherNodes) {
        ArrayList<TreeNode<T>> nodes = new ArrayList<>();
        nodes.add(rootNode);
        nodes.addAll(Arrays.asList(otherNodes));

        int nodesCount = nodes.size();
        int i = 0;
        do {
            int nullCounter = 0;
            for (int j = i; j <= i * 2; j++) {
                TreeNode<T> currentNode = nodes.get(j);
                if (currentNode == null) {
                    nullCounter++;
                    continue;
                }
                int leftChildIndex = (j - nullCounter) * 2 + 1;
                int rightChildIndex = (j - nullCounter) * 2 + 2;
                if (leftChildIndex < nodesCount) {
                    currentNode.setLeftChild(nodes.get(leftChildIndex));
                } else {
                    break;
                }
                if (rightChildIndex < nodesCount) {
                    currentNode.setRightChild(nodes.get(rightChildIndex));
                } else {
                    break;
                }
            }
            if (i != 0 && nullCounter == i * 2) {
                break;
            }
            i = i * 2 + 1;
        } while (i < nodesCount);

        return rootNode;
    }

    @Override
    public <T> TreeNode<T> convertToFullBinaryTree(TreeNode<T> rootNode) {
        if (rootNode == null) {
            return null;
        }

        rootNode.setLeftChild(convertToFullBinaryTree(rootNode.getLeftChild().orElse(null)));
        rootNode.setRightChild(convertToFullBinaryTree(rootNode.getRightChild().orElse(null)));

        if (rootNode.isLeaf() || rootNode.isFullBranch()) {
            return rootNode;
        }

        return rootNode.getLeftChild().orElse(rootNode.getRightChild().orElse(null));
    }

    @Override
    public <T> TreeNode<T>[] convertTreeToArray(TreeNode<T> rootNode) {
        List<TreeNode<T>> result = new ArrayList<>();

        treeToList(rootNode, result);

        return result.toArray(new TreeNode[result.size()]);
    }

    private <T> void treeToList(TreeNode<T> currentNode, List<TreeNode<T>> result) {
        result.add(currentNode);
        if (currentNode != null && !currentNode.isLeaf()) {
            treeToList(currentNode.getLeftChild().orElse(null), result);
            treeToList(currentNode.getRightChild().orElse(null), result);
        }
    }
}
