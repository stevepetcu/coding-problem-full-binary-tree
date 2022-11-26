package com.stefanpetcu.api.fullbinarytree.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TreeNodeTest {
    @Test
    void isLeaf_willReturnTrue_givenANodeWithoutAnyChildren() {
        TreeNode<String> rootNode = new TreeNode<>("root");
        TreeNode<String> firstLeaf = new TreeNode<>("firstLeaf");
        TreeNode<String> secondLeaf = new TreeNode<>("secondLeaf");
        rootNode.setLeftChild(firstLeaf).setRightChild(secondLeaf);

        assertTrue(firstLeaf.isLeaf());
        assertTrue(secondLeaf.isLeaf());
    }

    @Test
    void isLeaf_willReturnFalse_givenANodeWithAnyChildren() {
        TreeNode<String> rootNode = new TreeNode<>("root");
        TreeNode<String> firstLeaf = new TreeNode<>("firstLeaf");
        TreeNode<String> secondLeaf = new TreeNode<>("secondLeaf");

        rootNode.setLeftChild(firstLeaf).setRightChild(secondLeaf);
        assertFalse(rootNode.isLeaf());

        rootNode.setLeftChild(null);
        assertFalse(rootNode.isLeaf());

        rootNode.setLeftChild(firstLeaf).setRightChild(null);
        assertFalse(rootNode.isLeaf());
    }

    @Test
    void isFullBranch_willReturnTrue_givenARootOfAFullBranch() {
        TreeNode<String> rootNode = new TreeNode<>("root");
        TreeNode<String> firstLeaf = new TreeNode<>("firstLeaf");
        TreeNode<String> secondLeaf = new TreeNode<>("secondLeaf");
        rootNode.setLeftChild(firstLeaf).setRightChild(secondLeaf);

        assertTrue(rootNode.isFullBranch());
    }

    @Test
    void isFullBranch_willReturnFalse_givenARootOfThatDoesNotHaveTwoChildren() {
        TreeNode<String> rootNode = new TreeNode<>("root");
        TreeNode<String> leaf = new TreeNode<>("leaf");

        assertFalse(rootNode.isFullBranch());

        rootNode.setLeftChild(leaf);
        assertFalse(rootNode.isFullBranch());

        rootNode.setLeftChild(null).setRightChild(leaf);
        assertFalse(rootNode.isFullBranch());
    }
}
