package com.stefanpetcu.api.fullbinarytree.presentation;

import com.stefanpetcu.api.fullbinarytree.domain.TreeNode;
import com.stefanpetcu.api.fullbinarytree.presentation.ConvertTreeToFullBinaryRequest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConvertTreeToFullBinaryRequestTest {

    @Test
    void setNodes_willSetTreeNodes_givenArrayOfIntegers() {
        ConvertTreeToFullBinaryRequest<Integer> testSubject = new ConvertTreeToFullBinaryRequest<>();

        testSubject.setNodes(new Integer[] {1, 2, 3});

        List<TreeNode<Integer>> results = testSubject.getTreeNodes();

        assertEquals(3, results.size());
        assertEquals(1, results.get(0).getValue());
        assertEquals(2, results.get(1).getValue());
        assertEquals(3, results.get(2).getValue());
    }

    @Test
    void setNodes_willSetTreeNodes_givenArrayOfIntegersAndNulls() {
        ConvertTreeToFullBinaryRequest<Integer> testSubject = new ConvertTreeToFullBinaryRequest<>();

        testSubject.setNodes(new Integer[] {1, null, 3});

        List<TreeNode<Integer>> results = testSubject.getTreeNodes();

        assertEquals(3, results.size());
        assertEquals(1, results.get(0).getValue());
        assertNull(results.get(1));
        assertEquals(3, results.get(2).getValue());
    }
}
