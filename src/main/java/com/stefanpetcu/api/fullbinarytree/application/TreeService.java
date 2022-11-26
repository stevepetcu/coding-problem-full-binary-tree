package com.stefanpetcu.api.fullbinarytree.application;

import com.stefanpetcu.api.fullbinarytree.domain.TreeNode;

public interface TreeService {
    <T> TreeNode<T> buildBinaryTree(TreeNode<T> rootNode, TreeNode<T>... otherNodes);

    <T> TreeNode<T> convertToFullBinaryTree(TreeNode<T> rootNode);

    <T> TreeNode<T>[] convertTreeToArray(TreeNode<T> rootNode);
}
