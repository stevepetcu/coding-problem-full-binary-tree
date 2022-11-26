package com.stefanpetcu.api.fullbinarytree.presentation;

import com.stefanpetcu.api.fullbinarytree.domain.TreeNode;

public class FullBinaryTreeResponseDto<T> {
    private final TreeNode<T>[] tree;

    public FullBinaryTreeResponseDto(TreeNode<T>[] tree) {
        this.tree = tree;
    }

    public TreeNode<T>[] getTree() {
        return tree;
    }
}
