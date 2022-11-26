package com.stefanpetcu.api.fullbinarytree.application;

import com.stefanpetcu.api.fullbinarytree.domain.TreeNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TreeServiceImplTest {
    private final TreeServiceImpl testSubject = new TreeServiceImpl();

    @Test
    void buildBinaryTree_willReturnRootNodeOfABinaryTree_givenAnArrayOfThreeNodes() {
        TreeNode<Integer> rootNode = new TreeNode<>(1);
        TreeNode<Integer> leftNode = new TreeNode<>(2);
        TreeNode<Integer> rightNode = new TreeNode<>(3);

        TreeNode<Integer> resultRootNode = testSubject.buildBinaryTree(rootNode, leftNode, rightNode);

        assertSame(rootNode, resultRootNode);
        assertSame(leftNode, rootNode.getLeftChild().get());
        assertSame(rightNode, rootNode.getRightChild().get());
    }

    @Test
    void buildBinaryTree_willReturnRootNodeOfABinaryTree_givenAnArrayOfFiveNodes() {
        TreeNode<Integer> firstNode = new TreeNode<>(1);
        TreeNode<Integer> secondNode = new TreeNode<>(2);
        TreeNode<Integer> thirdNode = new TreeNode<>(3);
        TreeNode<Integer> fourthNode = new TreeNode<>(4);
        TreeNode<Integer> fifthNode = new TreeNode<>(5);

        TreeNode<Integer> resultRootNode = testSubject.buildBinaryTree(firstNode, secondNode, thirdNode, fourthNode, fifthNode);

        assertSame(firstNode, resultRootNode);
        assertSame(secondNode, firstNode.getLeftChild().get());
        assertSame(thirdNode, firstNode.getRightChild().get());
        assertSame(fourthNode, secondNode.getLeftChild().get());
        assertSame(fifthNode, secondNode.getRightChild().get());
    }

    @Test
    void buildBinaryTree_willReturnRootNodeOfABinaryTree_givenAnArrayOfNodesContainingNullNodes() {
        TreeNode<Integer> firstNode = new TreeNode<>(1);
        TreeNode<Integer> thirdNode = new TreeNode<>(3);
        TreeNode<Integer> fourthNode = new TreeNode<>(4);
        TreeNode<Integer> fifthNode = new TreeNode<>(5);

        TreeNode<Integer> resultRootNode = testSubject.buildBinaryTree(firstNode, null, thirdNode, fourthNode, fifthNode);

        assertSame(firstNode, resultRootNode);
        assertTrue(firstNode.getLeftChild().isEmpty());
        assertSame(thirdNode, firstNode.getRightChild().get());
        assertSame(fourthNode, thirdNode.getLeftChild().get());
        assertSame(fifthNode, thirdNode.getRightChild().get());
    }

    @Test
    void buildBinaryTree_willStopBuildingTree_givenArrayOfNodesWithFullRowOfNullNodes() {
        TreeNode<Integer> firstNode = new TreeNode<>(1);
        TreeNode<Integer> fourthNode = new TreeNode<>(4);
        TreeNode<Integer> fifthNode = new TreeNode<>(5);

        TreeNode<Integer> resultRootNode = testSubject.buildBinaryTree(firstNode, null, null, fourthNode, fifthNode);

        assertSame(firstNode, resultRootNode);
        assertTrue(firstNode.getLeftChild().isEmpty());
        assertTrue(firstNode.getRightChild().isEmpty());
        assertTrue(fourthNode.getLeftChild().isEmpty());
        assertTrue(fourthNode.getRightChild().isEmpty());
        assertTrue(fifthNode.getLeftChild().isEmpty());
        assertTrue(fifthNode.getRightChild().isEmpty());
    }

    @Test
    void buildBinaryTree_willReturnNull_givenArrayOfNodesStartingWithNull() {
        TreeNode<Integer> secondNode = new TreeNode<>(1);
        TreeNode<Integer> thirdNode = new TreeNode<>(2);

        assertNull(testSubject.buildBinaryTree(null, secondNode, thirdNode));
    }

    @Test
    void convertToFullBinaryTree_willReturnOriginalTree_givenAlreadyFullBinaryTree() {
        TreeNode<Integer> originalRoot = new TreeNode<>(0);
        TreeNode<Integer> firstNode = new TreeNode<>(1);
        TreeNode<Integer> secondNode = new TreeNode<>(2);
        TreeNode<Integer> thirdNode = new TreeNode<>(3);
        TreeNode<Integer> fourthNode = new TreeNode<>(4);

        TreeNode<Integer> resultTreeRoot = testSubject.convertToFullBinaryTree(
                testSubject.buildBinaryTree(originalRoot, firstNode, secondNode, thirdNode, fourthNode));

        assertSame(originalRoot, resultTreeRoot);
        assertSame(firstNode, resultTreeRoot.getLeftChild().get());
        assertSame(secondNode, resultTreeRoot.getRightChild().get());
        assertSame(thirdNode, firstNode.getLeftChild().get());
        assertSame(fourthNode, firstNode.getRightChild().get());
    }

    @Test
    void convertToFullBinaryTree_willReturnRoot_givenRootWithNoLeaves() {
        TreeNode<Integer> root = new TreeNode<>(1);

        TreeNode<Integer> resultTreeRoot = testSubject.convertToFullBinaryTree(testSubject.buildBinaryTree(root));

        assertSame(root, resultTreeRoot);
    }

    @ParameterizedTest
    @MethodSource("integerAndNullArrayProvider")
        // worst test ever, but example of parametrised test
    void convertToFullBinaryTree_willReturnLeftOrRightLeaf_givenRootWithEitherLeavesNull(Integer rootValue, Integer firstValue, Integer secondValue) {
        TreeNode<Integer> rootNode = new TreeNode<>(rootValue);
        TreeNode<Integer> firstNode = firstValue != null ? new TreeNode<>(firstValue) : null;
        TreeNode<Integer> secondNode = secondValue != null ? new TreeNode<>(secondValue) : null;

        TreeNode<Integer> resultTreeRoot = testSubject.convertToFullBinaryTree(
                testSubject.buildBinaryTree(rootNode, firstNode, secondNode));

        TreeNode<Integer> chosenNode = firstNode != null ? firstNode : secondNode;

        assertSame(chosenNode, resultTreeRoot);
        assertTrue(resultTreeRoot.getLeftChild().isEmpty());
        assertTrue(resultTreeRoot.getRightChild().isEmpty());
    }

    @Test
    void convertToFullBinaryTree_willRightChildOfOriginalRoot_givenNonFullBinaryTreeWithNullLeftNode() {
        TreeNode<Integer> originalRoot = new TreeNode<>(0);
        TreeNode<Integer> secondNode = new TreeNode<>(2);
        TreeNode<Integer> thirdNode = new TreeNode<>(3);
        TreeNode<Integer> fourthNode = new TreeNode<>(4);

        TreeNode<Integer> resultTreeRoot = testSubject.convertToFullBinaryTree(
                testSubject.buildBinaryTree(originalRoot, null, secondNode, thirdNode, fourthNode));

        assertSame(secondNode, resultTreeRoot);
        assertSame(thirdNode, secondNode.getLeftChild().get());
        assertSame(fourthNode, secondNode.getRightChild().get());
    }

    @Test
    void convertToFullBinaryTree_willLeftChildOfOriginalRoot_givenNonFullBinaryTreeWithNullRightNode() {
        TreeNode<Integer> originalRoot = new TreeNode<>(0);
        TreeNode<Integer> firstNode = new TreeNode<>(2);
        TreeNode<Integer> thirdNode = new TreeNode<>(3);
        TreeNode<Integer> fourthNode = new TreeNode<>(4);

        TreeNode<Integer> resultTreeRoot = testSubject.convertToFullBinaryTree(
                testSubject.buildBinaryTree(originalRoot, firstNode, null, thirdNode, fourthNode));

        assertSame(firstNode, resultTreeRoot);
        assertSame(thirdNode, firstNode.getLeftChild().get());
        assertSame(fourthNode, firstNode.getRightChild().get());
    }

    static Stream<Arguments> integerAndNullArrayProvider() {
        return Stream.of(
                Arguments.arguments(1, null, 2),
                Arguments.arguments(1, 2, null)
        );
    }

    @Test
    void convertToFullBinaryTree_willReturnOriginalRoot_givenRootWithNoLeaves() {
        TreeNode<Integer> rootNode = new TreeNode<>(1);

        TreeNode<Integer> resultTreeRoot = testSubject.convertToFullBinaryTree(
                testSubject.buildBinaryTree(rootNode, null, null));

        assertSame(rootNode, resultTreeRoot);
        assertTrue(resultTreeRoot.getLeftChild().isEmpty());
        assertTrue(resultTreeRoot.getRightChild().isEmpty());
    }

    @Test
    void convertToFullBinaryTree_willReturnOriginalRoot_givenRootWithLotsOfBranches() {
        TreeNode<Integer> rootNode = new TreeNode<>(0);
        TreeNode<Integer> firstNode = new TreeNode<>(1);
        TreeNode<Integer> secondNode = new TreeNode<>(2);
        TreeNode<Integer> thirdNode = new TreeNode<>(3);
        TreeNode<Integer> sixthNode = new TreeNode<>(4);
        TreeNode<Integer> eighthNode = new TreeNode<>(5);
        TreeNode<Integer> ninthNode = new TreeNode<>(6);
        TreeNode<Integer> tenthNode = new TreeNode<>(7);

        TreeNode<Integer> resultTreeRoot = testSubject.convertToFullBinaryTree(
                testSubject.buildBinaryTree(rootNode, firstNode, secondNode, thirdNode, null, null,
                        sixthNode, null, eighthNode, ninthNode, tenthNode));

        assertSame(rootNode, resultTreeRoot);
        assertSame(eighthNode, resultTreeRoot.getLeftChild().get());
        assertSame(sixthNode, resultTreeRoot.getRightChild().get());
        assertSame(ninthNode, sixthNode.getLeftChild().get());
        assertSame(tenthNode, sixthNode.getRightChild().get());
    }

    @Test
    void convertTreeToArray_willReturnArrayOfNodes_givenRootNode() {
        TreeNode<Integer> rootNode = new TreeNode<>(0);
        TreeNode<Integer> firstNode = new TreeNode<>(1);
        TreeNode<Integer> secondNode = new TreeNode<>(2);
        TreeNode<Integer> thirdNode = new TreeNode<>(3);
        TreeNode<Integer> fourthNode = new TreeNode<>(4);
        TreeNode<Integer> fifthNode = new TreeNode<>(5);

        TreeNode<Integer> resultTreeRoot = testSubject.buildBinaryTree(rootNode, firstNode, secondNode, thirdNode, fourthNode, fifthNode);
        TreeNode<Integer>[] nodesArray = testSubject.convertTreeToArray(resultTreeRoot);

        assertTrue(Arrays.equals(
                new TreeNode[]{rootNode, firstNode, thirdNode, fourthNode, secondNode, fifthNode, null},
                nodesArray
        ));
    }

    @Test
    void convertTreeToArray_willReturnArrayOfNodes_givenRootNodeWithNullChildren() {
        TreeNode<Integer> originalRoot = new TreeNode<>(0);
        TreeNode<Integer> firstNode = new TreeNode<>(1);
        TreeNode<Integer> thirdNode = new TreeNode<>(2);
        TreeNode<Integer> fourthNode = new TreeNode<>(3);

        TreeNode<Integer>[] treeNodesArray = testSubject.convertTreeToArray(
                testSubject.buildBinaryTree(originalRoot, firstNode, null, thirdNode, fourthNode));

        assertTrue(Arrays.equals(
                new TreeNode[]{originalRoot, firstNode, thirdNode, fourthNode, null},
                treeNodesArray
        ));
    }
}
